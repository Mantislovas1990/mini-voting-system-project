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
                        HibernateConfig.buildSessionFactory();
                        menuService.menuAddVoter(sc);
                        break;
                    case 2:
                        HibernateConfig.buildSessionFactory();
                        voterService.getAllVoters().forEach(System.out::println);
                        break;
                    case 3:
                        HibernateConfig.buildSessionFactory();
                        System.out.println("Total vote count: " + voterService.getTotalCount());
                        break;
                    case 4:
                        HibernateConfig.buildSessionFactory();
                        voterService.getVoteCountByCity().forEach(item -> System.out.println("City: " + item[0] +"\nCount: " + item[1]));
                        break;
                    case 5:
                        HibernateConfig.buildSessionFactory();
                        voterService.getVoteCountByGender().forEach(item -> System.out.println("Gender: " + item[0] +"\nCount: " + item[1]));
                        break;
                    case 6:
                        HibernateConfig.buildSessionFactory();
                        candidateService.getWinnerOfElection().forEach(item -> System.out.println("Winner is: " + item[0]+ " " + item[1] +"\nVote Count: " + item[2]));
                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

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
                "\n[4] -> WHO WON ELECTION \n[5] -> MOST ACTIVE VOTING CITY\n[6] -> MOST ACTIVE VOTING GENDER\n" +
                "[7] -> SHOW VOTE NUMBERS BY CANDIDATE\n[8] -> SAVE DATA TO FILE\n[9] -> GET DATA FROM FILE\n[0] -> CLOSE THE PROGRAM\n---------------------";
    }
}
