package source.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Roles {
    private static final int MIN_JOUEURS = 8;
    private static final int MAX_JOUEURS = 46;
    private static final int NB_VILLAGEOIS = 10; // 9 Villageois + 1 Villageois Pure
    private static final int NB_LOUPS_GAROUS = 6; // 4 Loups-Garous + 1 Grand Méchant Loup + 1 Infecte Père des Loups
    private static final int NB_THIRD_PARTY = 30; // 18 Villageois Spéciaux + 4 Solitaire + 5 Ambigu + 3 Spécifique

    private ArrayList<Role> roles;
    private HashMap<String, String[]> parseTable;

    public Roles(){
        roles = new ArrayList<Role>();

        load_roles();
    
    }

    private void load_roles() {
        // Ajout des rôles
        // 9 Villageois
        for(int i = 0; i < NB_VILLAGEOIS - 1; i++)
            roles.add(new Role("Villageois", "Vous êtes un villageois. Vous n'avez pas de pouvoir particulier.", Type.VILLAGEOIS, Team.VILLAGE, "../../images/villageois.png"));

        // 1 Villageois Pure
        roles.add(new Role("Villageois Pure", "Vous êtes le villageois pure. Vous n'avez pas de pouvoir particulier. Votre carte est à double face.", Type.VILLAGEOIS, Team.VILLAGE, "../../images/villageois_pure.png"));

        // 1 voyante
        roles.add(new Role("Voyante", "Vous êtes la voyante. Chaque nuit, vous pouvez espionner un joueur et découvrir sa véritable identité.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/voyante.png"));

        // 1 sorcière
        roles.add(new Role("Sorcière", "Vous êtes la sorcière. Chaque nuit, vous pouvez utiliser une potion de guérison pour sauver un joueur de la mort, ou une potion d'empoisonnement pour tuer un joueur.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/sorciere.png"));

        // 1 cupidon
        roles.add(new Role("Cupidon", "Vous êtes le cupidon. Chaque nuit, vous pouvez désigner deux joueurs qui seront amoureux. Si l'un des deux meurt, l'autre meurt de chagrin.", Type.VILLAGEOIS_SPECIAL, Team.NEUTRE,  "../../images/cupidon.png"));

        // 1 chasseur
        roles.add(new Role("Chasseur", "Vous êtes le chasseur. Si vous êtes tué, vous pouvez désigner un joueur qui mourra avec vous.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/chasseur.png"));

        // 1 petite fille
        roles.add(new Role("Petite Fille", "Vous êtes la petite fille. Chaque nuit, vous pouvez espionner les loups-garous. Si vous êtes démasquée, vous mourrez.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/petite_fille.png"));

        // 1 salvateur
        roles.add(new Role("Salvateur", "Vous êtes le salvateur. Chaque nuit, vous pouvez protéger un joueur de la mort.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/salvateur.png"));

        // 1 ancien
        roles.add(new Role("Ancien", "Vous êtes l'ancien. Si vous êtes tué, vous résistez à la mort. Vous ne pouvez utiliser ce pouvoir qu'une seule fois.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/ancien.png"));

        // 1 bouc émissaire
        roles.add(new Role("Bouc Emissaire", "Vous êtes le bouc émissaire. S'il y a ex-aequo durant le vote diurne, vous mourrez.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/bouc_emissaire.png"));

        // 1 idiot du village
        roles.add(new Role("Idiot du Village", "Vous êtes l'idiot du village. Si vous êtes tué, vous ne mourrez pas et vous ne pouvez plus voter.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/idiot_du_village.png"));

        // 2 les deux soeurs
        roles.add(new Role("Les Deux Soeurs", "Vous êtes l'une des soeurs. Vous connaissez l'identité de l'autre. Si l'une des deux meurt, l'autre meurt de chagrin.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/deux_soeurs.png"));

        // 3 les trois frères
        roles.add(new Role("Les Trois Frères", "Vous êtes l'un des frères. Vous connaissez l'identité des deux autres. Si l'un des trois meurt, les deux autres meurent de chagrin.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/trois_freres.png"));

        // 1 juge bègue
        roles.add(new Role("Juge Bègue", "Vous êtes le juge bègue. Lors de la 1ère nuit, vous choissisez un signe, qui pourra être utilisé une fois pendant la partie pour lancer un 2nd vote diurne.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/juge_begue.png"));

        // 1 renard
        roles.add(new Role("Renard", "Vous êtes le renard. Chaque nuit, vous pouvez flairer un joueur. Le meneur indiquera si l'un ou les 2 voisins font partis des loups-garous", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/renard.png"));

        // 1 montreur d'ours
        roles.add(new Role("Montreur d'Ours", "Vous êtes le montreur d'ours. Chaque jour, le meneur de jeu grognera si l'un de vos voisins est un loup-garou", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/montreur_d_ours.png"));

        // 1 chevalier à l'épée rouillée
        roles.add(new Role("Chevalier à l'Epée Rouillée", "Vous êtes le chevalier à l'épée rouillée. Si vous êtes tué par les loups-garous, le 1er loup-garou à votre gauche mourra.", Type.VILLAGEOIS_SPECIAL, Team.VILLAGE,  "../../images/chevalier_a_l_epée_rouillée.png"));

        // 1 voleur
        roles.add(new Role("Voleur", "Vous êtes le voleur. Lors de la 1ère nuit, vous pouvez voler l'identité d'un autre joueur.", Type.AMBIGU, Team.VILLAGE, "../../images/voleur.png"));

        // 1 servante dévouée
        roles.add(new Role("Servante Dévouée", "Vous êtes la servante dévouée. Lors de la mort d'un joueur, juste avant de révéler son rôle, vous pouvez vous l'approprier.", Type.AMBIGU, Team.VILLAGE, "../../images/servante_devouee.png"));
    
        // 1 comédien
        roles.add(new Role("Comédien", "Vous êtes le comédien. 3 rôles sont posés face cachée. Chaque nuit, vous avez la possibilité de devenir pour une seule nuit l'un des 3 rôles.", Type.AMBIGU, Team.VILLAGE, "../../images/comedien.png"));
    
        // 1 enfant sauvage
        roles.add(new Role("Enfant Sauvage", "Vous êtes l'enfant sauvage. Vous êtes un villageois. La 1ère nuit, vous choisissez un modèle. S'il meurt, vous devenez un loup-garou", Type.AMBIGU, Team.VILLAGE, "../../images/enfant_sauvage.png"));
    
        // 1 chien-loup
        roles.add(new Role("Chien-Loup", "Vous êtes le chien-loup. La 1ère nuit, vous choisirez entre devenir un chien (villageois) ou un loup (loup-garou)", Type.AMBIGU, Team.NEUTRE, "../../images/chien_loup.png"));
    
        // 1 ange
        roles.add(new Role("Ange", "Vous êtes l'ange. Si vous êtes tué par les villageois lors de la 1ère partie, vous gagnez la partie. Sinon, vous devenez un simple villageois", Type.SOLITAIRE, Team.NEUTRE, "../../images/ange.png"));
    
        // 1 joueur de flûte
        roles.add(new Role("Joueur de Flûte", "Vous êtes le joueur de flûte. Chaque nuit, vous pouvez charmer 2 joueur. Si tous les joueurs en vie sont charmés, vous gagnez la partie.", Type.SOLITAIRE, Team.NEUTRE, "../../images/joueur_de_flute.png"));
    
        // 1 abominable sectaire
        roles.add(new Role("Abominable Sectaire", "Vous êtes l'abominable sectaire. Au début de la partie, le meneur de jeu divise le groupe en 2 selon un critère. Votre objectif est qu'il n'y ait plus aucun opposant en vie.", Type.SOLITAIRE, Team.NEUTRE, "../../images/abominable_sectaire.png"));

        // 1 pyromane
        roles.add(new Role("Pyromane", "Vous êtes le pyromane. Chaque nuit, vous pouvez brûler une maison. Si vous brûlez la maison d'un loup-garou, il meurt.", Type.SPECIFIQUE, Team.VILLAGE, "../../images/pyromane.png"));

        // 1 corbeau
        roles.add(new Role("Corbeau", "Vous êtes le corbeau. Chaque nuit, vous pouvez désigner un joueur qui sera accusé de meurtre le lendemain.", Type.SPECIFIQUE, Team.VILLAGE, "../../images/corbeau.png"));

        // 1 gitane
        roles.add(new Role("Gitane", "Vous êtes la gitane. Une nuit sur 2, vous pouvez choisir parmi une liste une question qui sera posé au 1er défunt.", Type.SPECIFIQUE, Team.VILLAGE, "../../images/gitane.png"));

        // 4 loup-garous
        for(int i = 0; i < NB_LOUPS_GAROUS - 2; i++)
            roles.add(new Role("Loup-Garou", "Vous êtes un loup-garou. Chaque nuit, vous pouvez dévorer un joueur. Vous gagnez si tous les villageois sont morts.", Type.LOUP_GAROU, Team.LOUPS, "../../images/loup_garou.png"));
    
        // 1 loup-garou blanc
        roles.add(new Role("Loup-Garou Blanc", "Vous êtes le loup-garou blanc. Chaque nuit, vous pouvez dévorer un joueur. Mais une 1 nuit sur 2, vous pouvez manger un loup-garou. Vous devez gagner seul.", Type.SOLITAIRE, Team.NEUTRE, "../../images/loup_garou_blanc.png"));

        // 1 grand méchant loup
        roles.add(new Role("Grand Méchant Loup", "Vous êtes le grand méchant loup. Chaque nuit, vous pouvez dévorer un joueur. Mais 1 nuit sur 2, vous pouvez manger un 2ème joueur.", Type.LOUP_GAROU, Team.LOUPS, "../../images/grand_mechant_loup.png"));

        // 1 infecte père des loups
        roles.add(new Role("Infecte Père des Loups", "Vous êtes l'infecte père des loups. Chaque nuit, vous pouvez dévorer un joueur. Mais une fois par partie, vous pouvez changer un joueur en loup.", Type.LOUP_GAROU, Team.LOUPS, "../../images/infecte_pere_des_loups.png"));

        loadParseTable();
    }

    private void loadParseTable(){
        parseTable = new HashMap<String, String[]>();
        for(Role role : roles){
            String[] roleInfo = new String[4];
            roleInfo[0] = role.getDescription();
            roleInfo[1] = role.getType().toString();
            roleInfo[2] = role.getTeam().toString();
            roleInfo[3] = role.getImagePath();
            parseTable.put(role.getName(), roleInfo);
        }
    }

    public static int getMinJoueurs() {
        return MIN_JOUEURS;
    }

    public static int getMaxJoueurs() {
        return MAX_JOUEURS;
    }

    public static int getNbVillageois() {
        return NB_VILLAGEOIS;
    }

    public static int getNbLoupsGarous() {
        return NB_LOUPS_GAROUS;
    }

    public static int getNbThirdParty() {
        return NB_THIRD_PARTY;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public Role getRole(String name) {
        for(Role role : roles)
            if(role.getName().equals(name))
                return role;
        return null;
    }

    public HashMap<String, String[]> getParseTable() {
        return parseTable;
    }

    private ArrayList<Role> getRolesByType(Type type) {
        ArrayList<Role> rolesByType = new ArrayList<Role>();
        for(Role role : roles)
            if(role.getType() == type)
                rolesByType.add(role);
        return rolesByType;
    }

    // Fonctions pour obtenir aléatoirement des rôles 

    // Villageois
    public ArrayList<Role> getVillageois(int nbVillageois) {
        ArrayList<Role> villageois = getRolesByType(Type.VILLAGEOIS);
        ArrayList<Role> villageoisRandom = new ArrayList<Role>();
        Random randomObj = new Random();

        for(int i = 0; i < nbVillageois; i++) {
            int random = randomObj.nextInt(villageois.size());
            villageoisRandom.add(villageois.get(random));
            villageois.remove(random);
        }

        return villageoisRandom;
    }

    // Loups-Garous
    public ArrayList<Role> getLoupsGarous(int nbLoupsGarous) {
        ArrayList<Role> loupsGarous = getRolesByType(Type.LOUP_GAROU);
        ArrayList<Role> loupsGarousRandom = new ArrayList<Role>();
        Random randomObj = new Random();

        for(int i = 0; i < nbLoupsGarous; i++) {
            int random = randomObj.nextInt(loupsGarous.size());
            loupsGarousRandom.add(loupsGarous.get(random));
            loupsGarous.remove(random);
        }

        return loupsGarousRandom;
    }

    // Obtenir une liste aléatoire de rôles sans compter les villageois et les loups-garous
    // Voyante, Sorcière, Cupidon et Chasseur sont obligatoires
    // Règle spéciale : Si une "Les Deux Soeurs" est prise, on doit prendre la deuxième "Les Deux Soeurs"
    // Règle spéciale : Si un "Les Trois Frères" est prise, on doit prendre le deuxième et le troisième "Les Trois Frères"
    public ArrayList<Role> getThirdParty(int nbRole, boolean extensionVillage, boolean extensionNouvelleLune){
        ArrayList<Role> thirdParty = new ArrayList<Role>();
        ArrayList<Role> randomRoles = new ArrayList<Role>();
        Random randomObj = new Random();

        // Purifions la liste des rôles des villageois et des loups-garous
        for(Role role : roles)
            if(role.getType() != Type.VILLAGEOIS && role.getType() != Type.LOUP_GAROU)
                thirdParty.add(role);

        if(!extensionNouvelleLune)
            thirdParty.remove(getRole("Gitane")); // La gitane vient avec l'extension Nouvelle Lune

        if(!extensionVillage){
            thirdParty.remove(getRole("Pyromane")); // Le pyromane vient avec l'extension Village
            thirdParty.remove(getRole("Corbeau")); // Le corbeau vient avec l'extension Village
        }

        // On ajoute les rôles obligatoires
        randomRoles.add(getRole("Voyante"));
        randomRoles.add(getRole("Sorcière"));
        randomRoles.add(getRole("Cupidon"));
        randomRoles.add(getRole("Chasseur"));

        // On les supprime de la liste des rôles
        thirdParty.remove(getRole("Voyante"));
        thirdParty.remove(getRole("Sorcière"));
        thirdParty.remove(getRole("Cupidon"));
        thirdParty.remove(getRole("Chasseur"));

        // On ajoute les rôles aléatoires
        Role newAddedRole = null;
        int nbRoleToAdd = nbRole - 4;
        for(int i = 0; i < nbRoleToAdd; i++) {
            int random = randomObj.nextInt(thirdParty.size());
            newAddedRole = thirdParty.get(random);

            if(newAddedRole.getName().equals("Les Deux Soeurs")) {
                if(nbRoleToAdd - i >= 2){
                    // On ajoute les deux soeurs
                    for(int j = 0; j < 2; j++)
                        randomRoles.add(newAddedRole);

                    thirdParty.remove(newAddedRole);
                    // On incrémente i de 1
                    i++;
                } else {
                    i--;
                }

            } else if(newAddedRole.getName().equals("Les Trois Frères")) {
                if(nbRoleToAdd - i >= 3){
                    // On ajoute les trois frères
                    for(int j = 0; j < 3; j++)
                        randomRoles.add(newAddedRole);
                    
                    thirdParty.remove(newAddedRole);
                    // On incrémente i de 2
                    i += 2;
                } else {
                    i--;
                }

            } else {
                // On ajoute le rôle aléatoire
                randomRoles.add(newAddedRole);
                // On supprime le rôle aléatoire de la liste des rôles
                thirdParty.remove(newAddedRole);
            }
        }

        return randomRoles;
    }
}
