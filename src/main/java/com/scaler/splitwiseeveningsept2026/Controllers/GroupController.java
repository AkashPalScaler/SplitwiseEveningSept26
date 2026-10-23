package com.scaler.splitwiseeveningsept2026.Controllers;

import com.scaler.splitwiseeveningsept2026.DTO.ResponseStatus;
import com.scaler.splitwiseeveningsept2026.DTO.SettleUpGroupRequestDTO;
import com.scaler.splitwiseeveningsept2026.DTO.SettleUpGroupResponseDTO;
import com.scaler.splitwiseeveningsept2026.DTO.Transaction;
import com.scaler.splitwiseeveningsept2026.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Controller
public class GroupController {
    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public SettleUpGroupResponseDTO settleUp(SettleUpGroupRequestDTO requestDTO){
        SettleUpGroupResponseDTO responseDTO = new SettleUpGroupResponseDTO();
        try{
            List<Transaction> transactions = groupService.settleUpGroup(requestDTO.getGroupId());
            responseDTO.setTransactions(transactions);
            responseDTO.setMessage("Clear the following txns to settle up the group");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("Failure to calculate the txns for settlement due to error - " + e.getMessage());
        }
        return responseDTO;
    }
}
