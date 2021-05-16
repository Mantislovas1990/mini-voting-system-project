package lt.codeacademy.model;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.service.CandidateService;
import lt.codeacademy.service.MenuService;
import lt.codeacademy.service.VoterService;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private final VoterService voterService = new VoterService();
    private final CandidateService candidateService = new CandidateService();
    private final MenuService menuService = new MenuService(voterService,candidateService);

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
                        voterService.getAllVoters().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Total vote count: " + voterService.getTotalCount());
                        break;
                    case 4:
                        System.out.println("VILNIUS VOTE COUNT: " +voterService.getVilniusVoteCount()
                                +"\nKAUNAS VOTE COUNT: " + voterService.getKaunasVoteCount());
                        break;
                    case 5:
                        System.out.println("MALE VOTE COUNT: " +voterService.getMaleVoteCount()
                        +"\nFEMALE VOTE COUNT: " + voterService.getFemaleVoteCount());
                        break;
                    case 6:
                        System.out.println("Winner of ELECTION: "+ candidateService.getWinnerOfElection());
                        break;
                    case 7:
                        System.out.println("CHOOSE ID TO DELETE\n");
                        voterService.getAllVoters().forEach(System.out::println);
                        voterService.delete(sc.nextLong());
                        break;
                    case 0:
                        run = false;
                        System.out.println("\nPROGRAM IS CLOSING................\n=====================\nHAVE A GREAT DAY!");
                        HibernateConfig.closeSessionFactory();
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
                "\n[4] -> VOTE COUNT BY CITY\n[5] -> VOTE COUNT BY GENDER\n[6] -> WINNER OF ELECTION\n" +
                "[7] -> DELETE CANDIDATE\n[0] -> CLOSE THE PROGRAM\n---------------------";
    }
}
