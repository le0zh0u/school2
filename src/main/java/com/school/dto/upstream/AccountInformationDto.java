package com.school.dto.upstream;

import com.school.domain.AccountInfoDO;

/**
 * Created by zhouchunjie on 16/3/31.
 */
public class AccountInformationDto extends AccountInfoDO {

    private String universityName;
    private String collegeName;
    private String majorName;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public static AccountInformationDto convertFromAccountInfoDO(AccountInfoDO accountInfoDO) {
        AccountInformationDto accountInformationDto = new AccountInformationDto();

        accountInformationDto.setId(accountInfoDO.getId());
        accountInformationDto.setStuCode(accountInfoDO.getStuCode());
        accountInformationDto.setName(accountInfoDO.getName());
        accountInformationDto.setNickName(accountInfoDO.getNickName());
        accountInformationDto.setGender(accountInfoDO.getGender());
        accountInformationDto.setHeadPortrait(accountInfoDO.getHeadPortrait());
        accountInformationDto.setUniversityCode(accountInfoDO.getUniversityCode());
        accountInformationDto.setCollegeCode(accountInfoDO.getCollegeCode());
        accountInformationDto.setMajorCode(accountInfoDO.getMajorCode());

        return accountInformationDto;
    }
}
