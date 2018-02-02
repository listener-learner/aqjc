package com.tyust.dao.en;

import com.tyust.bean.en.EnTestReport;
import com.tyust.bean.en.EnTestReportExample;
import java.util.List;

public interface EnTestReportDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int countByExample(EnTestReportExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int deleteByExample(EnTestReportExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int deleteByPrimaryKey(String environmentTestReportId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    void insert(EnTestReport record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    void insertSelective(EnTestReport record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    List<EnTestReport> selectByExample(EnTestReportExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    EnTestReport selectByPrimaryKey(String environmentTestReportId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int updateByExampleSelective(EnTestReport record, EnTestReportExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int updateByExample(EnTestReport record, EnTestReportExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int updateByPrimaryKeySelective(EnTestReport record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_TEST_REPORT
     *
     * @ibatorgenerated
     */
    int updateByPrimaryKey(EnTestReport record);
}