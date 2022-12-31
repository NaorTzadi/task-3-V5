import java.util.Arrays;
import java.util.Scanner;
public class RealEstate {
    private Property[] PROPERTIES;
    private  int propertyCount;

    City city = new City();
    User user = new User();
    public RealEstate(){
        propertyCount=0;
        PROPERTIES=new Property[1];
    }
    public static void MainMenu(RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);
        String menuNumber;
        do {
            System.out.println("press 1 to create a new account");
            System.out.println("press 2 to login ");
            System.out.println("press 3 to end interaction");
            menuNumber = scanner.next();
        } while (!menuNumber.equals("1") && !menuNumber.equals("2") && !menuNumber.equals("3"));
        if (menuNumber.equals("1")) {
            RealEstate.createUser(realEstate);
        } else {
            if (menuNumber.equals("2")) {
                RealEstate.userLogin(realEstate);
            } else {
                while (menuNumber.equals("3")) {
                    break;
                }
            }
        }
    }
    private static void SubMenu(int position,String name,RealEstate realEstate){
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.println("press 1 post a new property");
            System.out.println("press 2 to remove a property");
            System.out.println("press 3 to see all the properties on the system");
            System.out.println("press 4 to see all properties posted by you");
            System.out.println("press 5 to search for a property by filters");
            System.out.println("press 6 to log out and return to the main menu");
            number = scanner.nextInt();
        } while (number != 1 && number != 2 && number != 3 && number != 4 && number != 5 && number != 6);
        if (number == 1) {
            postNewProperty(position,name,realEstate);
        }else{
            if(number == 2){
                removeProperty(position,name,realEstate);
            }else{
                if(number == 3){
                    printAllProperties(position,name,realEstate);
                }else{
                    if(number == 4){
                        printProperties(position,name,realEstate);
                    }else{
                        if(number == 5){

                        }else{
                            if(number == 6){
                                MainMenu(realEstate);
                            }else{
                                System.out.println("invalid input");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void createUser(RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("pick a username");
        String name = scanner.next();
        for (int i = 0; i < User.userNames.length; i++) {
            if (name.equals(User.userNames[i])) {
                System.out.println("this username is already taken.");
                createUser(realEstate);
            }
        }

        User.userNames = new String[User.userNames.length + 1];
        User.userNames[User.userNames.length - 1] = name;

        String pass;
        do {
            System.out.println("pick a password with at least 5 characters,make sure you add at least one of these characters:%,$,_");
            pass = scanner.nextLine();
        } while (pass.length() < 5 || !pass.contains("%") && !pass.contains("$") && !pass.contains("_"));

        User.passWord = new String[User.passWord.length + 1];
        User.passWord[User.passWord.length - 1] = pass;

        String number;
        do {
            System.out.println("please enter your phone number");
            number = scanner.nextLine();
        } while (!number.matches("05[0-9]{8}"));

        User.phoneNumber = Arrays.copyOf(User.phoneNumber, User.phoneNumber.length + 1);
        User.phoneNumber[User.phoneNumber.length - 1] = number;

        String purpose;
        do {
            System.out.println("are you a user or a seller ?");
            System.out.println("type user if you are a user, type seller if you are a seller");
            purpose = scanner.nextLine();
        } while (!purpose.matches("user") && !purpose.matches("seller"));
        if(purpose.matches("user")){
            User.limitOfPropertyPosts=Arrays.copyOf(User.limitOfPropertyPosts, User.limitOfPropertyPosts.length+1);
            User.limitOfPropertyPosts[User.limitOfPropertyPosts.length-1]=2;
        }else{
            User.limitOfPropertyPosts=Arrays.copyOf(User.limitOfPropertyPosts, User.limitOfPropertyPosts.length+1);
            User.limitOfPropertyPosts[User.limitOfPropertyPosts.length-1]=5;
        }
        User.sellerOrUser = Arrays.copyOf(User.sellerOrUser, User.sellerOrUser.length + 1);
        User.sellerOrUser[User.sellerOrUser.length - 1] = purpose;
        System.out.println("user successfully created");
        System.out.println();

        MainMenu(realEstate);
    }
    public static User userLogin(RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String pass;
        int n=0;
        int p=0;
        int i;
        int position=0;
        if (User.userNames[0] != null) {
            System.out.println("please enter your username");
            name = scanner.nextLine();
            for ( i = 0; i < User.userNames.length; i++) {
                if (name.equals(User.userNames[i])) {
                    n = 1;
                    position=i;
                }
            }
            System.out.println("please enter your password");
            pass = scanner.nextLine();
            for (int j = 0; j < User.passWord.length; j++) {
                if (pass.equals(User.passWord[j])) {
                    p = 1;
                }
            }
            if (p == 1 && n==1) {
                System.out.println(User.userNames[position]);
                SubMenu(position,name,realEstate);
            } else {
                System.out.println("check if your username and password are correct");
                MainMenu(realEstate);
                return null;
            }
        } else {
            System.out.println("it appears you're our first user, dont expect any special treatment");
            System.out.println("choose the first option this time");
            MainMenu(realEstate);
        }
        return null;
    }
    public static boolean postNewProperty(int position,String name, RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);
        if(User.limitOfPropertyPosts[position]==0){
            System.out.println("sorry, it seems you reached the limit of posts you can upload");
            return false;
        }
        for (int i = 0; i < City.city.length; i++) {
            System.out.println(City.city[i]);
        }
        System.out.println("");
        System.out.println("please type a name of a city from the list above");
        String city = scanner.next();

        for (int i =0; i < City.city.length; i++) {
            if (City.city[i].equals(city)) {
                System.out.println();
                System.out.println(City.streets[i*2] + " , " + City.streets[i*2 + 1]);
                System.out.println("please type a name of a street from the above");
                String street = scanner.next();

                if (City.streets[i*2].equals(street) || City.streets[i*2 + 1].equals(street)) {
                    System.out.println("what is the type of property?");
                    System.out.println("press 1 for a standard apartment");
                    System.out.println("press 2 for penthouse apartment");
                    System.out.println("press 3 for a private house");
                    String flat;
                    int type = scanner.nextInt();
                    if (type == 1 || type == 2) {
                        System.out.println("in what flat is the property at?");
                        flat = scanner.next();
                        if(!flat.matches("\\d+")){
                            System.out.println("invalid input");
                            System.exit(1);
                            return  false;
                        }
                    }else{
                        flat=null;
                    }
                    if (type == 3|| type ==2 || type == 1) {
                        System.out.println("how many rooms there are in the property?");
                        String rooms = scanner.next();
                        if (rooms.matches("\\d+")) {
                            System.out.println("what is the number of the property?");
                            String numberOfProperty = scanner.next();
                            if (numberOfProperty.matches("\\d+")) {
                                System.out.println("is the property for rent or sale?");
                                String rentOrSale = scanner.next();
                                boolean saleOrRent=false;
                                if (rentOrSale.equals("rent") || rentOrSale.equals("sale")){
                                    if (rentOrSale.equals("rent")) {
                                        saleOrRent = false;
                                    }
                                    if (rentOrSale.equals("sale")) {
                                        saleOrRent = true;
                                    }
                                    System.out.println("what is the price of the property?");
                                    String price = scanner.next();
                                    if (price.matches("\\d+")) {
                                        User.limitOfPropertyPosts[position]=User.limitOfPropertyPosts[position]-1;
                                        realEstate.PROPERTIES[realEstate.propertyCount]=new Property(name, city, street, type,
                                                flat, rooms, price, numberOfProperty, saleOrRent);
                                        realEstate.PROPERTIES=Arrays.copyOf(realEstate.PROPERTIES,realEstate.PROPERTIES.length+1);
                                        realEstate.propertyCount++;
                                        SubMenu(position,name,realEstate);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("invalid input");
        return false;
    }
    public static void removeProperty(int position,String name,RealEstate realEstate){
        if(User.limitOfPropertyPosts[position]==5 && User.sellerOrUser[position].equals("seller")
                || User.limitOfPropertyPosts[position]==2 && User.sellerOrUser[position].equals("user")){
            System.out.println("you didnt upload any properties yet");
            System.exit(1);
        }
        int counter=0;
        int[] storeI=new int[1];
        for (int i = 0; i < realEstate.PROPERTIES.length-1; i++) {
            if (realEstate.PROPERTIES[i].getNAME_OF_SELLER().equals(name)){
                storeI[counter]=i;
                counter++;
                storeI=Arrays.copyOf(storeI,storeI.length+1);
                System.out.println("Property number "+counter+" found for "+name+":");
                System.out.print("city: "+realEstate.PROPERTIES[i].getCITY_OF_PROPERTY()+"|");
                System.out.print("street: "+realEstate.PROPERTIES[i].getSTREET_OF_PROPERTY()+"|");
                System.out.print("type: "+realEstate.PROPERTIES[i].getTYPE_OF_PROPERTY()+"|");
                System.out.print("flat: "+realEstate.PROPERTIES[i].getFLAT_OF_PROPERTY()+"|");
                System.out.print("number of property: "+realEstate.PROPERTIES[i].getNUMBER_OF_PROPERTY()+"|");
                System.out.print("number of rooms: "+realEstate.PROPERTIES[i].getNUMBER_OF_ROOMS()+"|");
                System.out.print("price: "+realEstate.PROPERTIES[i].getPRICE_OF_PROPERTY()+"|");
                System.out.print("is for sale: "+realEstate.PROPERTIES[i].isIF_FOR_SALE());
                System.out.println();
            }
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("choose the number of property you want to remove");
        int number= scanner.nextInt();
               for (int i = 0; i < realEstate.PROPERTIES.length; i++) {
            if (realEstate.PROPERTIES[storeI[number-1]].getNAME_OF_SELLER().equals(name)) {
                realEstate.PROPERTIES[storeI[number-1]] = null;
                for (int j = i; j < realEstate.PROPERTIES.length - 1; j++) {
                    realEstate.PROPERTIES[storeI[number-1]] = realEstate.PROPERTIES[storeI[number]];
                }
                System.out.println("property was removed");
                realEstate.PROPERTIES=Arrays.copyOf(realEstate.PROPERTIES,realEstate.PROPERTIES.length-1);
                realEstate.propertyCount--;
                SubMenu(position,name,realEstate);
            }
        }
    }
    public static void printAllProperties(int position,String name,RealEstate realEstate){
        for(int i=0; i<realEstate.PROPERTIES.length; i++){
            System.out.print("city: "+realEstate.PROPERTIES[i].getCITY_OF_PROPERTY()+"|");
            System.out.print("street: "+realEstate.PROPERTIES[i].getSTREET_OF_PROPERTY()+"|");
            System.out.print("type: "+realEstate.PROPERTIES[i].getTYPE_OF_PROPERTY()+"|");
            System.out.print("flat: "+realEstate.PROPERTIES[i].getFLAT_OF_PROPERTY()+"|");
            System.out.print("number of property: "+realEstate.PROPERTIES[i].getNUMBER_OF_PROPERTY()+"|");
            System.out.print("number of rooms: "+realEstate.PROPERTIES[i].getNUMBER_OF_ROOMS()+"|");
            System.out.print("price: "+realEstate.PROPERTIES[i].getPRICE_OF_PROPERTY()+"|");
            System.out.print("is for sale: "+realEstate.PROPERTIES[i].isIF_FOR_SALE());
            System.out.println();
        }
        System.out.println("1 for main menu | 2 for sub menu");
        Scanner scanner=new Scanner(System.in);
        String whereTo=scanner.next();
        if(whereTo.equals("1")){
            MainMenu(realEstate);
        }else{
            if (whereTo.equals("2")){
                SubMenu(position,name,realEstate);
            }
        }
    }
  public static void printProperties(int position,String name,RealEstate realEstate) {
      if(User.limitOfPropertyPosts[position]==5 && User.sellerOrUser[position].equals("seller")
              || User.limitOfPropertyPosts[position]==2 && User.sellerOrUser[position].equals("user")){
          System.out.println("you didnt upload any properties yet");
          System.exit(1);
      }
      int counter=0;
      for (int i = 0; i < realEstate.PROPERTIES.length-1; i++) {
          if (realEstate.PROPERTIES[i].getNAME_OF_SELLER().equals(name)){
              counter++;
              System.out.println("Property number "+counter+" found for "+name+":");
              System.out.print("city: "+realEstate.PROPERTIES[i].getCITY_OF_PROPERTY()+"|");
              System.out.print("street: "+realEstate.PROPERTIES[i].getSTREET_OF_PROPERTY()+"|");
              System.out.print("type: "+realEstate.PROPERTIES[i].getTYPE_OF_PROPERTY()+"|");
              System.out.print("flat: "+realEstate.PROPERTIES[i].getFLAT_OF_PROPERTY()+"|");
              System.out.print("number of property: "+realEstate.PROPERTIES[i].getNUMBER_OF_PROPERTY()+"|");
              System.out.print("number of rooms: "+realEstate.PROPERTIES[i].getNUMBER_OF_ROOMS()+"|");
              System.out.print("price: "+realEstate.PROPERTIES[i].getPRICE_OF_PROPERTY()+"|");
              System.out.print("is for sale: "+realEstate.PROPERTIES[i].isIF_FOR_SALE());
              System.out.println();
          }
      }
      SubMenu(position,name,realEstate);
  }
  
}