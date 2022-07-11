package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

import java.util.Map;

public class EditSummaryDtoRequest {
    private Map<String, SkillDtoRequest> editSummaryDtoRequestMap;
    private String token;

    public EditSummaryDtoRequest(Map<String, SkillDtoRequest> editSummaryDtoRequestMap, String token) {
        this.editSummaryDtoRequestMap = editSummaryDtoRequestMap;
        this.token = token;
    }

    public Map<String, SkillDtoRequest> getEditSummaryDtoRequestMap() {
        return editSummaryDtoRequestMap;
    }

    public String getToken() {
        return token;
    }

    public void validate(SkillDtoRequest skillDtoRequest) throws ServerException {
        if (skillDtoRequest.getProficiencyLevel() == 0) {
            throw new ServerException(ServerErrorCode.WRONG_PROFICIENCY_LEVEL);
        }
    }
}
