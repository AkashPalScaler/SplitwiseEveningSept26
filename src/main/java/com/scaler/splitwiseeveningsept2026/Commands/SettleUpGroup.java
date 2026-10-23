package com.scaler.splitwiseeveningsept2026.Commands;

import com.scaler.splitwiseeveningsept2026.Controllers.GroupController;
import com.scaler.splitwiseeveningsept2026.Controllers.UserController;
import com.scaler.splitwiseeveningsept2026.DTO.ResponseStatus;
import com.scaler.splitwiseeveningsept2026.DTO.SettleUpGroupRequestDTO;
import com.scaler.splitwiseeveningsept2026.DTO.SettleUpGroupResponseDTO;
import com.scaler.splitwiseeveningsept2026.DTO.Transaction;
import com.scaler.splitwiseeveningsept2026.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// settlegroup group_id
@Component
public class SettleUpGroup implements Command {
    @Autowired
    GroupController groupController;
    @Override
    public boolean matchCommand(String input) {
        String command = input.split(" ")[0];
        return command.equalsIgnoreCase("settlegroup");
    }

    @Override
    public void execute(String input) {
        Long groupId = Long.parseLong(input.split(" ")[1]);
        SettleUpGroupRequestDTO settleUpGroupRequestDTO = new SettleUpGroupRequestDTO();
        settleUpGroupRequestDTO.setGroupId(groupId);
        SettleUpGroupResponseDTO responseDTO = groupController.settleUp(settleUpGroupRequestDTO);
        if(responseDTO.getStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println("Successfully calculated the txns");
            for(Transaction transaction : responseDTO.getTransactions()){
                System.out.println(transaction.getFromUserId() + " needs to pay " + transaction.getAmount() + " to " + transaction.getToUserId());
            }
        }else{
            System.out.println(responseDTO.getMessage());
        }
    }
}
