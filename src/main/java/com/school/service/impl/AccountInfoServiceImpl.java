package com.school.service.impl;

import com.school.dao.AccountInfoDOMapper;
import com.school.domain.AccountInfoDO;
import com.school.dto.upstream.AccountInformationDto;
import com.school.enums.CollegeEnum;
import com.school.enums.MajorEnum;
import com.school.enums.UniversityEnum;
import com.school.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouchunjie on 16/3/30.
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    private static final Logger logger = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoDOMapper accountInfoDOMapper;

    public AccountInformationDto getAccountInfoDOByUserId(Integer userId) {
        logger.info("start get account info do by user id");

        AccountInfoDO accountInfoDO = accountInfoDOMapper.selectByPrimaryKey(userId);
        AccountInformationDto accountInformationDto = AccountInformationDto.convertFromAccountInfoDO(accountInfoDO);

        //转换code为name
        accountInformationDto
                .setUniversityName(UniversityEnum.getNameByCode(accountInformationDto.getUniversityCode()));
        accountInformationDto.setCollegeName(CollegeEnum.getNameByCode(accountInformationDto.getCollegeCode()));
        accountInformationDto.setMajorName(MajorEnum.getNameByCode(accountInformationDto.getMajorCode()));


        return accountInformationDto;
    }

    public void updateAccountInfo(AccountInfoDO accountInfoDO) throws Exception {
        logger.info("start update account info");
        if (accountInfoDO.getId() == null) {
            throw new Exception("no params");
        }
        accountInfoDOMapper.updateByPrimaryKeySelective(accountInfoDO);
    }
}
