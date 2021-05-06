package lt.codeacademy.model;

import lt.codeacademy.model.serializer.LoadJason;
import lt.codeacademy.model.serializer.SaveJason;
import lt.codeacademy.service.MenuService;
import lt.codeacademy.service.VoteService;
import lt.codeacademy.service.VoterService;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private final VoteService voteServiceImpl = new VoteService();
    private final VoterService voterService = new VoterService();
    private final MenuService menuService = new MenuService(voteServiceImpl,voterService);

    public void run() throws IOException {

        Scanner sc = new Scanner(System.in);

        boolean run = true;
        while (run) {
            System.out.println(getMenuText());

            boolean hasNextInt = sc.hasNextInt();

            if (hasNextInt) {
                int chooseAction = menuService.action(sc.nextInt());
                switch (chooseAction) {
                    case 1:
                        menuService.menuAddVoter(sc);
                        break;
                    case 2:
                        voteServiceImpl.getAllVoters().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println(voteServiceImpl.getTotalCountOfVotes());
                        break;
                    case 4:
                        System.out.println(voteServiceImpl.getWinnerOfElection());
                        break;
                    case 5:
                        System.out.println(voteServiceImpl.getMostActiveVotingCity());
                        break;
                    case 6:
                        System.out.println(voteServiceImpl.getMostActiveGender());
                        break;
                    case 7:
                        System.out.println(voteServiceImpl.getCandidateVoteCount(menuService.candidates(sc)));
                        break;
                    case 8:
                        SaveJason saveJason = new SaveJason();
                        saveJason.saveRecordsToFile(voteServiceImpl);
                        break;
                    case 9:
                        LoadJason loadJason = new LoadJason();
                        voteServiceImpl.setVotingList(loadJason.loadRecordsFromFile());
                        break;
                    case 0:
                        run = false;
                        System.out.println("\nPROGRAM IS CLOSING................\n=====================\nHAVE A GREAT DAY!");
                        break;
                    default:
                        System.out.println("ERROR!\n==============\nUNRECOGNISED INPUT!");
                        break;
                }
            } else {
                System.out.println("\nERROR! UNKNOWN COMMAND!");
            }
            sc.nextLine();
        }
    }

    private String getMenuText() {
        return "\nCHOOSE ACTION:\n---------------------\n[1] -> ADD YOUR VOTE\n[2] -> GET LIST OF VOTES \n[3] -> GET VOTE COUNT" +
                "\n[4] -> WHO WON ELECTION \n[5] -> MOST ACTIVE VOTING CITY\n[6] -> MOST ACTIVE VOTING GENDER\n" +
                "[7] -> SHOW VOTE NUMBERS BY CANDIDATE\n[8] -> SAVE DATA TO FILE\n[9] -> GET DATA FROM FILE\n[0] -> CLOSE THE PROGRAM\n---------------------";
    }
}
