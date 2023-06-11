package source.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Simulator {
    private HashMap<Role, Integer> selectedRoles;
    private int nbPlayers;
    private final int nbVillageois, nbLoupsGarous, nbThirdParty;
    

    public Simulator(int nbPlayers, boolean avecExtensionVillage, boolean avecExtensionNouvelleLune) throws NbPlayersIncorrectException {
        this.nbPlayers = nbPlayers;
        if(nbPlayers < Roles.getMinJoueurs() || nbPlayers > Roles.getMaxJoueurs()){
            throw new NbPlayersIncorrectException("Le nombre de joueurs doit être compris entre 8 et 46");
        }

        selectedRoles = new HashMap<Role, Integer>();

        // Une proportion correcte pour une partie est 
        // 1/4 de villageois
        // 1/4 de loups-garous
        // 1/2 de rôles tiers
        int nbPlayersDividedByFour = dividedByFour(this.nbPlayers);

        if(nbPlayersDividedByFour > Roles.getNbVillageois()){
            nbVillageois = Roles.getNbVillageois();
        } else {
            nbVillageois = nbPlayersDividedByFour;
        }

        if(nbPlayersDividedByFour > Roles.getNbLoupsGarous()){
            nbLoupsGarous = Roles.getNbLoupsGarous();
        } else {
            nbLoupsGarous = nbPlayersDividedByFour;
        }

        nbThirdParty = nbPlayers - nbVillageois - nbLoupsGarous;


        Roles roles = new Roles();

        // On a besoin des 3 listes = villageois, loups-garous, tiers
        ArrayList<Role> villageois = roles.getVillageois(nbVillageois);
        ArrayList<Role> loupsGarous = roles.getLoupsGarous(nbLoupsGarous);
        ArrayList<Role> thirdParty = roles.getThirdParty(nbThirdParty, avecExtensionVillage, avecExtensionNouvelleLune);

        // On ajoute les rôles à la hashmap
        // Chaque rôle agit comme une clé et le nombre de fois qu'il apparaît comme valeur
        
        rolesMapped(villageois, loupsGarous, thirdParty);

    }
  
    public Simulator(String filename) throws NbPlayersIncorrectException , IOException{
        Scanner sc = new Scanner(new File(filename));
        if(!sc.hasNextInt()){
            sc.close();
            throw new NbPlayersIncorrectException("Le fichier ne contient pas le nombre de joueurs");
        }

        nbPlayers = sc.nextInt();
        if(nbPlayers < Roles.getMinJoueurs() || nbPlayers > Roles.getMaxJoueurs()){
            sc.close();
            throw new NbPlayersIncorrectException("Le nombre de joueurs doit être compris entre 8 et 46");
        }

        nbVillageois = sc.nextInt();
        nbLoupsGarous = sc.nextInt();
        nbThirdParty = sc.nextInt();

        boolean verification = nbVillageois + nbLoupsGarous + nbThirdParty == nbPlayers ? true : false;
        if(!verification){
            sc.close();
            throw new NbPlayersIncorrectException("Le nombre de villageois, loups-garous et tiers ne correspond pas au nombre de joueurs");
        }

        selectedRoles = new HashMap<Role, Integer>();
        Roles roles = new Roles();
        HashMap<String, String[]> parsedRole = roles.getParseTable();
        
        int clean = 0, bad = 0, neutral = 0;
        for(int i = 0; i < nbPlayers; i++){
            String roleName = sc.nextLine();
            Role role = new Role(roleName, parsedRole.get(roleName)[0], Type.fromString(parsedRole.get(roleName)[1]), Team.fromString(parsedRole.get(roleName)[2]), parsedRole.get(roleName)[3]);
            if(role.getTeam() == Team.VILLAGE){
                clean++;
            } else if(role.getTeam() == Team.LOUPS){
                bad++;
            } else {
                neutral++;
            }
            selectedRoles.put(role, selectedRoles.get(role) + 1);
        }
        
        // Vérication du nombre de joueurs et du nombre de villageois, loups-garous et tiers
        if(clean != nbVillageois){
            sc.close();
            throw new NbPlayersIncorrectException("Le nombre de villageois enregistré (" + clean + ") ne correspond pas au nombre de villageois attendu (" + nbVillageois + ")");
        }
        if(bad != nbLoupsGarous){
            sc.close();
            throw new NbPlayersIncorrectException("Le nombre de loups-garous enregistré (" + bad + ") ne correspond pas au nombre de loups-garous attendu (" + nbLoupsGarous + ")");
        }
        if(neutral != nbThirdParty){
            sc.close();
            throw new NbPlayersIncorrectException("Le nombre de tiers enregistré (" + neutral + ") ne correspond pas au nombre de tiers attendu (" + nbThirdParty + ")");
        }

        // On vérifie que le fichier ne contient plus rien
        if(sc.hasNext()){
            sc.close();
            throw new IOException("Le fichier contient trop de lignes");
        }

        sc.close();
    }
  
    public void save(String filename) throws IOException{
        FileWriter fw = new FileWriter(filename);
        fw.write(nbPlayers + " " + nbVillageois + " " + nbLoupsGarous + " " + nbThirdParty + "\n");
        for(Role role : selectedRoles.keySet()){
            fw.write(role.getName());
        }
        fw.close();
    }

    public ArrayList<String[]> getRoleToShow(){
        ArrayList<String[]> roleToShow = new ArrayList<String[]>();
        String previousRole = "";
        for(Role role : selectedRoles.keySet()){
            // On ne veut pas de doublons
            if(!role.getName().equals(previousRole)){
                String[] roleInfo = new String[4];
                roleInfo[0] = role.getName();
                roleInfo[1] = role.getDescription();
                roleInfo[2] = role.getType().toString();
                roleInfo[3] = role.getTeam().toString();
                roleToShow.add(roleInfo);
            }
            previousRole = role.getName();
        }

        return roleToShow;
    }

    public HashMap<Role, Integer> getSelectedRoles(){
        return selectedRoles;
    }

    private void rolesMapped(ArrayList<Role> villageois, ArrayList<Role> loupsGarous, ArrayList<Role> thirdParty) {
        for(Role role : villageois){
            if(selectedRoles.containsKey(role)){
                selectedRoles.put(role, selectedRoles.get(role) + 1);
            } else {
                selectedRoles.put(role, 1);
            }
        }

        for(Role role : loupsGarous){
            if(selectedRoles.containsKey(role)){
                selectedRoles.put(role, selectedRoles.get(role) + 1);
            } else {
                selectedRoles.put(role, 1);
            }
        }

        for(Role role : thirdParty){
            if(selectedRoles.containsKey(role)){
                selectedRoles.put(role, selectedRoles.get(role) + 1);
            } else {
                selectedRoles.put(role, 1);
            }
        }
    }

    private int dividedByFour(int nbPlayers) {
        double nbPlayersDouble = (double) nbPlayers;
        double result = nbPlayersDouble / 4;
        if(result - (int) result < 0.5)
            return (int) result;
        else
            return (int) result + 1;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public int getNbVillageois() {
        return nbVillageois;
    }

    public int getNbLoupsGarous() {
        return nbLoupsGarous;
    }

    public int getNbThirdParty() {
        return nbThirdParty;
    }
 
}
