package com.scaler.splitwiseeveningsept2026;

import com.scaler.splitwiseeveningsept2026.Commands.Command;
import com.scaler.splitwiseeveningsept2026.Commands.CommandRegistry;
import com.scaler.splitwiseeveningsept2026.Commands.Login;
import com.scaler.splitwiseeveningsept2026.Commands.RegisterCommand;
import com.scaler.splitwiseeveningsept2026.Controllers.GroupController;
import com.scaler.splitwiseeveningsept2026.Controllers.UserController;
import com.scaler.splitwiseeveningsept2026.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SplitwiseEveningSept2026Application implements CommandLineRunner {

    Scanner scanner = new Scanner(System.in);
    @Autowired
    UserController userController;

    CommandRegistry commandRegistry;
    // multiple controller
    @Autowired
    DataGenerator dataGenerator;
    @Autowired
    GroupController groupController;

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseEveningSept2026Application.class, args);
    }
    public void initialiseRegistry(){
        CommandRegistry commandRegistry = new CommandRegistry();

        //Without Factory
        commandRegistry.addCommand(new Login());
        commandRegistry.addCommand(new RegisterCommand());

        //With factory
        List<String> commands = List.of("login", "register", "create_group");
        commands.forEach(command -> {
            commandRegistry.addCommand(CommandFactory.getCommand(command));
        });
    }
    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Please enter your commands");
//        String input = scanner.nextLine();
//        initialiseRegistry();
//        commandRegistry.matchAndExecute(input);
//
//
//        //Direct Factory use (Cons - maintain singletons for each command or else every request will create a new object of the command)
//        String input1 = scanner.nextLine();
//        String command1 = Arrays.asList(input.split(" ")).get(0);
//        Command command = CommandFactory.getCommand(command1);
//        command.execute(input1);
//        dataGenerator.generateData();

        SettleUpGroupRequestDTO settleUpGroupRequestDTO = new SettleUpGroupRequestDTO();
        settleUpGroupRequestDTO.setGroupId(1l);
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

// CLI
// Git, aws sdk, grep, ls, docker, kubernetes(kubectl)

// git commit -m "message"
// app_name command_name inputs

// register username userpassword useremail
// create_group uid description 3 member_id1 member_id2 member_id3
// add_expense uid description amount 5 uid1 amount1 type1 uid2 amount2 type2 uid3 amount3 type3 uid4 amount4 type4 uid5 amount5 type5
