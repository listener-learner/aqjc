package com.tyust.dao.en;

import com.tyust.bean.en.EnApplyInfo;
import com.tyust.bean.en.EnApplyInfoExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class EnApplyInfoDAOImpl extends SqlMapClientDaoSupport implements EnApplyInfoDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public EnApplyInfoDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int countByExample(EnApplyInfoExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("ENVIRONMENT_APPLY_INFO.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int deleteByExample(EnApplyInfoExample example) {
        int rows = getSqlMapClientTemplate().delete("ENVIRONMENT_APPLY_INFO.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int deleteByPrimaryKey(String environmentApplyId) {
        EnApplyInfo key = new EnApplyInfo();
        key.setEnvironmentApplyId(environmentApplyId);
        int rows = getSqlMapClientTemplate().delete("ENVIRONMENT_APPLY_INFO.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public void insert(EnApplyInfo record) {
        getSqlMapClientTemplate().insert("ENVIRONMENT_APPLY_INFO.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public void insertSelective(EnApplyInfo record) {
        getSqlMapClientTemplate().insert("ENVIRONMENT_APPLY_INFO.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    @SuppressWarnings("unchecked")
    public List<EnApplyInfo> selectByExample(EnApplyInfoExample example) {
        List<EnApplyInfo> list = getSqlMapClientTemplate().queryForList("ENVIRONMENT_APPLY_INFO.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public EnApplyInfo selectByPrimaryKey(String environmentApplyId) {
        EnApplyInfo key = new EnApplyInfo();
        key.setEnvironmentApplyId(environmentApplyId);
        EnApplyInfo record = (EnApplyInfo) getSqlMapClientTemplate().queryForObject("ENVIRONMENT_APPLY_INFO.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int updateByExampleSelective(EnApplyInfo record, EnApplyInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ENVIRONMENT_APPLY_INFO.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int updateByExample(EnApplyInfo record, EnApplyInfoExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("ENVIRONMENT_APPLY_INFO.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int updateByPrimaryKeySelective(EnApplyInfo record) {
        int rows = getSqlMapClientTemplate().update("ENVIRONMENT_APPLY_INFO.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    public int updateByPrimaryKey(EnApplyInfo record) {
        int rows = getSqlMapClientTemplate().update("ENVIRONMENT_APPLY_INFO.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table ENVIRONMENT_APPLY_INFO
     *
     * @ibatorgenerated
     */
    private static class UpdateByExampleParms extends EnApplyInfoExample {
        private Object record;

        public UpdateByExampleParms(Object record, EnApplyInfoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}