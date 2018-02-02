package com.tyust.common.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class ImageUtils {
	private File srcFile;//Դ�ļ�
	private File destFile;//Ŀ���ļ�
	private double angle;//��ת�Ƕ�
	private float quality;//ͼƬ����
	private double scale;//���ű���
	private int width;//ԭͼƬ���
	private int height;//ԭͼƬ�߶�
	private int givenWidth;//ָ���Ŀ��
	private int givenHeight;//ָ���ĸ߶�
	private boolean fixedGivenSize;//�Ƿ񱣳�ԭͼ����
	private Color bgcolor;//����ͼƬ�ı���
	private boolean keepRatio;//�Ƿ񱣳ֱ���
	private ArrayList<Watermark> watermarkArr;

	public ImageUtils(File srcFile) {
		this.srcFile = srcFile;
	}

	/**
	 * ��ʼ��ͼƬ����
	 */
	private void init() {
		this.destFile = null;
		this.angle = 0d;
		this.quality = 0.75f;
		this.scale = 0d;
		this.width = 0;
		this.height = 0;
		this.fixedGivenSize = false;
		this.keepRatio = false;
		this.bgcolor = Color.BLACK;
		this.watermarkArr = new ArrayList<Watermark>();
	}

	public ImageUtils keepRatio(boolean keepRatio) {
		this.keepRatio = keepRatio;
		return this;
	}

	/**
	 * ָ��һ��ˮӡ�ļ�
	 * 
	 * @param watermark
	 *            {@link Watermark}
	 * @return {@link ImageUtils}
	 */
	public ImageUtils watermark(Watermark watermark) {
		this.watermarkArr.add(watermark);
		return this;
	}

	/**
	 * ָ�����ˮӡ�ļ�
	 * 
	 * @param watermarkArr
	 *            {@link ArrayList}
	 * @return {@link ImageUtils}
	 */
	public ImageUtils watermarkArray(ArrayList<Watermark> watermarkArr) {
		this.watermarkArr.addAll(watermarkArr);
		return this;
	}

	/**
	 * ָ��Դ�ļ�ͼƬ
	 * 
	 * @param srcImage
	 *            {@link File}
	 * @return {@link ImageUtils}
	 */
	public static ImageUtils fromFile(File srcImage) {
		ImageUtils image = new ImageUtils(srcImage);
		image.init();
		return image;
	}

	/**
	 * ������������
	 * 
	 * @param scale
	 *            ��������
	 * @return {@link ImageUtils}
	 */
	public ImageUtils scale(double scale) {
		if (scale <= 0) {
			throw new IllegalStateException("scale value error!");
		}
		this.scale = scale;
		return this;
	}

	/**
	 * ���ɵ�ͼƬ�Ƿ��Ը����Ĵ�С����
	 * 
	 * @param fixedGivenSize
	 *            {@link Boolean}
	 * @return {@link ImageUtils}
	 */
	public ImageUtils fixedGivenSize(boolean fixedGivenSize) {
		this.fixedGivenSize = fixedGivenSize;
		return this;
	}

	/**
	 * ָ������ͼƬ�Ŀ��
	 * 
	 * @param width
	 *            {@link Integer} ���
	 * @return {@link ImageUtils}
	 */
	public ImageUtils width(int width) {
		if (width <= 1) {
			throw new IllegalStateException("width value error!");
		}
		this.width = width;
		return this;
	}

	/**
	 * ָ������ͼƬ�ĸ߶�
	 * 
	 * @param height
	 *            {@link Integer} �߶�
	 * @return {@link ImageUtils}
	 */
	public ImageUtils height(int height) {
		if (height <= 1) {
			throw new IllegalStateException("height value error!");
		}
		this.height = height;
		return this;
	}

	/**
	 * ָ������ͼƬ�Ŀ�Ⱥ͸߶�
	 * 
	 * @param width
	 *            {@link Integer} ���
	 * @param height
	 *            {@link Integer} �߶�
	 * @return {@link ImageUtils}
	 */
	public ImageUtils size(int width, int height) {
		if (width <= 1 || height <= 1) {
			throw new IllegalStateException("width or height value error!");
		}
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * ָ����תͼƬ�Ƕ�
	 * 
	 * @param angle
	 *            {@link Double} ��תͼƬ�ĽǶ�
	 * @return {@link ImageUtils}
	 */
	public ImageUtils rotate(double angle) {
		this.angle = angle;
		return this;
	}

	/**
	 * ѹ��ͼƬ������
	 * 
	 * @param quality
	 *            {@link Float}
	 * @return
	 */
	public ImageUtils quality(float quality) {
		this.quality = quality;
		return this;
	}

	/**
	 * ���ñ�����ɫ
	 * 
	 * @param bgcolor
	 * @return
	 */
	public ImageUtils bgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
		return this;
	}

	/**
	 * ָ������ͼƬ���ļ�
	 * 
	 * @param destFile
	 *            {@link File}
	 */
	public void toFile(File destFile) {
		this.destFile = destFile;
		BufferedImage srcImage = null;
		try {
			srcImage = ImageIO.read(this.srcFile);
			if (this.scale > 0 && this.width == 0 && this.height == 0) {
				this.width = (int) (srcImage.getWidth() * this.scale);
				this.height = (int) (srcImage.getHeight() * this.scale);
			}
			if (this.angle != 0) {
				try {
					srcImage = this.rotateImage(srcImage);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("rotate error!");
					return;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("read image error!");
			return;
		}
		BufferedImage destImage = this.resize(srcImage);
		if (this.keepRatio) {
			destImage = this.keepImageRatio(destImage, this.givenWidth, this.givenHeight);
		}
		if (this.watermarkArr != null) {
			for (Watermark watermark : watermarkArr) {
				destImage = watermark.apply(destImage);
			}
		}
		try {
			this.makeImage(destImage);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("create image error!");
		}
	}

	/**
	 * ����ͼƬ��ԭ������������ԭͼƬ
	 * 
	 * @param img
	 *            {@link BufferedImage} ԭͼƬ
	 * @param targetWidth
	 *            {@link Integer} Ŀ����
	 * @param targetHeight
	 *            {@link Integer} Ŀ��߶�
	 * @return ���ؼ���������
	 */
	private BufferedImage keepImageRatio(BufferedImage img, int targetWidth, int targetHeight) {
		int sourceWidth = img.getWidth();
		int sourceHeight = img.getHeight();
		int x = 0;
		int y = 0;
		int drawWidth = targetWidth;
		int drawHeight = targetHeight;

		double sourceRatio = (double) sourceWidth / (double) sourceHeight;
		double targetRatio = (double) targetWidth / (double) targetHeight;

		/*
		 * If the ratios are not the same, then the appropriate width and height
		 * must be picked.
		 */
		if (Double.compare(sourceRatio, targetRatio) != 0) {
			if (sourceRatio > targetRatio) {
				drawHeight = (int) Math.round(targetWidth / sourceRatio);
			} else {
				drawWidth = (int) Math.round(targetHeight * sourceRatio);
			}
		}
		x = (targetWidth - drawWidth) / 2;
		y = (targetHeight - drawHeight) / 2;
		targetWidth = (targetWidth == 0) ? 1 : targetWidth;
		targetHeight = (targetHeight == 0) ? 1 : targetHeight;
		/*
		 * BufferedImage resizedImage = Utils.createImage(img, targetWidth,
		 * targetHeight, this.bgcolor);
		 */
		int type = BufferedImage.TYPE_INT_ARGB;
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		Utils.setRenderingHint(g);
		if (this.bgcolor != null) {
			g.setPaint(this.bgcolor);
			g.fillRect(0, 0, targetWidth, targetHeight);
		}
		g.drawImage(img, x, y, drawWidth, drawHeight, null);
		g.dispose();

		return resizedImage;
	}

	/**
	 * ��������ͼƬ��С
	 * 
	 * @param srcImage
	 * @return
	 */
	private BufferedImage resize(BufferedImage srcImage) {
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		if (this.width > 0 && this.height > 0) {
			if (this.fixedGivenSize) {
				this.givenWidth = this.width;
				this.givenHeight = this.height;
				if (!this.keepRatio) {
					width = this.width;
					height = this.height;
				}
			}
			if (this.keepRatio) {
				int drawWidth = this.width;
				int drawHeight = this.height;
				double sourceRatio = (double) width / (double) height;
				double targetRatio = (double) this.width / (double) this.height;

				if (Double.compare(sourceRatio, targetRatio) != 0) {
					if (sourceRatio > targetRatio) {
						drawHeight = (int) Math.round(this.width / sourceRatio);
					} else {
						drawWidth = (int) Math.round(this.height * sourceRatio);
					}
				}
				if (!this.fixedGivenSize) {
					this.givenWidth = drawWidth;
					this.givenHeight = drawHeight;
				}
				width = drawWidth;
				height = drawHeight;
			}
		} else if (this.scale > 0) {
			width = (int) (width * this.scale);
			height = (int) (height * this.scale);
		} else if (this.width > 0 && this.height == 0) {
			height = this.width * height / width;
			width = this.width;
		} else if (this.width == 0 && this.height > 0) {
			width = this.height * width / height;
			height = this.height;
		}
		if (width <= 1 || height <= 1) {
			throw new IllegalStateException("width or height value error!");
		}
		this.width = width;
		this.height = height;

		this.givenWidth = (this.givenWidth == 0 ? width : this.givenWidth);
		this.givenHeight = (this.givenHeight == 0 ? height : this.givenHeight);

		return Utils.createImage(srcImage, width, height, this.bgcolor);
	}

	/**
	 * Returns a {@link BufferedImage} with the specified image type, where the
	 * graphical content is a copy of the specified image.
	 * 
	 * @param img
	 *            The image to copy.
	 * @param imageType
	 *            The image type for the image to return.
	 * @return A copy of the specified image.
	 */
	public BufferedImage copy(BufferedImage img, int imageType) {
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage newImage = new BufferedImage(width, height, imageType);
		Graphics g = newImage.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public void makeImage(BufferedImage newImage) throws IOException {
		String fileExtension = getExtension(destFile);
		if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")
				|| fileExtension.equalsIgnoreCase("bmp")) {
			newImage = this.copy(newImage, BufferedImage.TYPE_INT_RGB);
		}

		ImageWriter imgWriter = ImageIO.getImageWritersByFormatName(fileExtension).next();
		ImageWriteParam imgWriteParam = imgWriter.getDefaultWriteParam();
		if (imgWriteParam.canWriteCompressed()) {
			if (fileExtension.equalsIgnoreCase("bmp")) {
				imgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				imgWriteParam.setCompressionType("BI_RGB");
			} else if (fileExtension.equalsIgnoreCase("gif")) {
				imgWriteParam.setCompressionMode(ImageWriteParam.MODE_COPY_FROM_METADATA);
			} else {
				imgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				imgWriteParam.setCompressionQuality(this.quality);
			}
		}
		ImageOutputStream outputStream = ImageIO.createImageOutputStream(destFile);
		imgWriter.setOutput(outputStream);
		IIOImage outputImage = new IIOImage(newImage, null, null);
		imgWriter.write(null, outputImage, imgWriteParam);
		imgWriter.dispose();
		outputStream.close();
	}

	private BufferedImage rotateImage(BufferedImage img) throws IOException {
		int width = img.getWidth();
		int height = img.getHeight();

		BufferedImage newImage;

		double[][] newPositions = new double[4][];
		newPositions[0] = this.calculatePosition(0, 0);
		newPositions[1] = this.calculatePosition(width, 0);
		newPositions[2] = this.calculatePosition(0, height);
		newPositions[3] = this.calculatePosition(width, height);

		double minX = Math.min(Math.min(newPositions[0][0], newPositions[1][0]),
				Math.min(newPositions[2][0], newPositions[3][0]));
		double maxX = Math.max(Math.max(newPositions[0][0], newPositions[1][0]),
				Math.max(newPositions[2][0], newPositions[3][0]));
		double minY = Math.min(Math.min(newPositions[0][1], newPositions[1][1]),
				Math.min(newPositions[2][1], newPositions[3][1]));
		double maxY = Math.max(Math.max(newPositions[0][1], newPositions[1][1]),
				Math.max(newPositions[2][1], newPositions[3][1]));

		int newWidth = (int) Math.round(maxX - minX);
		int newHeight = (int) Math.round(maxY - minY);

		// newImage = new BufferedImageBuilder(newWidth, newHeight).build();
		newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = newImage.createGraphics();
		Utils.setRenderingHint(g);
		if (this.bgcolor != null) {
			g.setPaint(this.bgcolor);
			g.fillRect(0, 0, newWidth, newHeight);
		}
		/*
		 * TODO consider RenderingHints to use. The following are hints which
		 * have been chosen to give decent image quality. In the future, there
		 * may be a need to have a way to change these settings.
		 */
		double w = newWidth / 2.0;
		double h = newHeight / 2.0;

		int centerX = (int) Math.round((newWidth - width) / 2.0);
		int centerY = (int) Math.round((newHeight - height) / 2.0);
		g.rotate(Math.toRadians(angle), w, h);
		g.drawImage(img, centerX, centerY, null);
		g.dispose();
		return newImage;
	}

	private double[] calculatePosition(double x, double y) {
		double angle = this.angle;
		angle = Math.toRadians(angle);

		double nx = (Math.cos(angle) * x) - (Math.sin(angle) * y);
		double ny = (Math.sin(angle) * x) + (Math.cos(angle) * y);

		return new double[] { nx, ny };
	}

	/**
	 * �����ļ���ʽ
	 * 
	 * @param f
	 *            {@link File} �ļ�
	 * @return �����ļ���ʽ
	 */
	private static String getExtension(File f) {
		String fileName = f.getName();
		if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length() - 1) {
			int lastIndex = fileName.lastIndexOf('.');
			return fileName.substring(lastIndex + 1);
		}
		return null;
	}

}