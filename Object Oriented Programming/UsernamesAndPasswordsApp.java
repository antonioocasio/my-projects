/*

Project Name: UsernamesAndPasswords
Author: Tony Ocasio
Purpose of program: to hold usernames and passwords for different apps, websites and any other
	login information
How to use: Enter the app/website name, enter the username or email, enter the password and 
	click add. The program will show a preview of the various websites, usernames and password in
	the text box. When the file menu is clicked, there is an option to save it. By using
	 the terminal you can select whether you would like to save it as a	JSON or a text file.
	 There is a clear menu item that will clear the list of websites, usernames, and passwords
	 There is also an option to open a previously saved JSON or text file.
	Clicking more and help will show how to use the program.
Model class: UsernamesAndPasswords - stores all usernames and passwords
View class: UandPFrame - creates the actual GUI
Controller class: UsernameAndPasswordController - brings the view and model classes together
Future enhancements: add the ability to save to a database and have the option to encrypt the file 
	with a password that will be asked for when opening. 

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


class UsernamesAndPasswords{
    // holds attributes of each username, password, and website
    private String username, password, website;

    public UsernamesAndPasswords(String wSiteIn, String uNameIn, String pWordIn){
        setWebsite(wSiteIn);
        setUsername(uNameIn);
        setPassword(pWordIn);
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String usernameIn){
        this.username = usernameIn;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String passwordIn){
        this.password = passwordIn;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(String websiteIn){
        this.website = websiteIn;
    }



    public String toString(){
        return String.format("%s %s %s", getWebsite(),
                getUsername(), getPassword());
    }
}


class UsernamesAndPasswordsController{
    ArrayList<UsernamesAndPasswords> upInfo;

    // saves the file
    public static boolean saveToTextFile(ArrayList<UsernamesAndPasswords> uandp, String fname){
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(
                    new FileWriter(new File(fname))));
//            for(int i = 0; i < uandp.size(); i++){
//                UsernamesAndPasswords u =
//                pw.println()
//            }
            for(UsernamesAndPasswords u:uandp){
                pw.println(u.getWebsite());
                pw.println(u.getUsername());
                pw.println(u.getPassword());
            }
            pw.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // displays a message when help is clicked
    public static void displayHelp(){
        JOptionPane.showMessageDialog(null, "Enter " +
                "website, username and password. \nClick add to add it to " +
                "the list. \nSelect the website and click remove to remove " +
                "the information for the selected website. \n" +
                "Click save/open and follow directions in terminal to complete.");
    }

    // this class reads text file
    public static ArrayList<UsernamesAndPasswords> readUserAndPass(String fname){
        ArrayList<UsernamesAndPasswords> result = new ArrayList<UsernamesAndPasswords>();
        try {
            File f = new File(fname);
            Scanner fsc = new Scanner(f);
            String website, username, password;
            UsernamesAndPasswords up;
            while(fsc.hasNextLine()){
                website = fsc.nextLine();
                username = fsc.nextLine();
                password = fsc.nextLine();
                up = new UsernamesAndPasswords(website, username, password);
                result.add(up);
            }
            fsc.close();
            return result;
        } catch (Exception ex){
            return null;
        }
    }
    public static boolean writeUandPToJSON(String fname, ArrayList<UsernamesAndPasswords> uandp){
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fname))));
            JSONArray array = new JSONArray();
            JSONObject obj;
            for(UsernamesAndPasswords u:uandp){
                obj = new JSONObject();
                obj.put("website", u.getWebsite());
                obj.put("username", u.getUsername());
                obj.put("password", u.getPassword());
                array.add(obj);
            }
            JSONObject allUsernamesAndPasswords = new JSONObject();
            allUsernamesAndPasswords.put("UsernamesAndPasswords", array);
            pw.println(allUsernamesAndPasswords.toJSONString());
            pw.close();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public static ArrayList<UsernamesAndPasswords> readUsernamesAndPasswordsFromJSON(String fname){
        try {
            ArrayList<UsernamesAndPasswords> result = new ArrayList<UsernamesAndPasswords>();
            FileReader fr = new FileReader(new File(fname));
            JSONParser jp = new JSONParser();
            JSONObject mainObj = (JSONObject)jp.parse(fr);
            JSONArray uandp = (JSONArray)mainObj.get("UsernamesAndPasswords");
            Iterator itr = uandp.iterator();
            String website, username, password;
            while (itr.hasNext()){
                JSONObject usernameAndPassword = (JSONObject)itr.next();
                website = (usernameAndPassword.get("website").toString());
                username = (usernameAndPassword.get("username").toString());
                password = (usernameAndPassword.get("password").toString());
                result.add(new UsernamesAndPasswords(website, username, password));
            }
            fr.close();
            return result;
        } catch(Exception ex){
            return null;
        }
    }
}

class UandPFrame extends JFrame implements ActionListener{
    public UandPFrame(){
        setupUI();
    }

    public void setupUI(){
        // sets default properites of the window
        setTitle("Usernames and Passwords");
        setBounds(800, 200, 750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // creates the contentPane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // creates buttonPanel and the buttons and adds to contentPane
        JPanel buttonPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnRemove = new JButton("Remove");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRemove);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // adds the file menu
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu more = new JMenu("More");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem clear = new JMenuItem("Clear All");
        file.add(save);
        file.add(open);
        file.add(clear);
        more.add(help);
        mb.add(file);
        mb.add(more);
        setJMenuBar(mb);

        // adds textfield for input to the infoPanel and contentPane
        JTextField username = new JTextField();
        JTextField password = new JTextField();
        JTextField website = new JTextField();

        // adds labels to tell user what to enter
        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");
        JLabel lblWeb = new JLabel("Website:");
        username.setPreferredSize(new Dimension(75, 20));
        password.setPreferredSize(new Dimension(75, 20));
        website.setPreferredSize(new Dimension(75, 20));

        // creates panel to store labels and text fields
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        infoPanel.add(lblWeb);
        infoPanel.add(website);
        infoPanel.add(lblUser);
        infoPanel.add(username);
        infoPanel.add(lblPass);
        infoPanel.add(password);
        contentPane.add(infoPanel, BorderLayout.NORTH);

        // creates a JList in the middle to display usernames
        DefaultListModel<String> list1 = new DefaultListModel<>();
        JList<String> list = new JList<>(list1);
        JScrollPane jsp = new JScrollPane(list);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        list.setBounds(100, 100, 20, 200);
        contentPane.add(jsp, BorderLayout.CENTER);

        // adds actionListeners to buttons and menu items
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().equalsIgnoreCase("") ||
                         password.getText().equalsIgnoreCase("")||
                        website.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "Must " +
                            "enter text in all fields.");
                } else {
                    list1.addElement(website.getText());
                    list1.addElement(username.getText());
                    list1.addElement(password.getText());
                }
            }
        });

        // if user selects clear from menu, delete everything from list
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.removeAllElements();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1){
                    list1.remove(selectedIndex);
                }
            }
        });

        // creates upc to control the save, help, open file
        UsernamesAndPasswordsController upc = new UsernamesAndPasswordsController();
        // opens a file
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner sc = new Scanner(System.in);
                String fname, type;
                System.out.println("Enter 1 for text file or 2 for JSON:");
                type = sc.nextLine();
                try {
                    // when the user selects 1, will open a text file
                    if (type.equalsIgnoreCase("1")){
                        System.out.println("Enter name of file: ");
                        fname = sc.nextLine();
                        ArrayList<UsernamesAndPasswords> userandpass = upc.readUserAndPass(fname);

                        for(UsernamesAndPasswords u:userandpass){
                            list1.addElement(u.getWebsite());
                            list1.addElement(u.getUsername());
                            list1.addElement(u.getPassword());
                        }
                        System.out.println("Done.");
                        // when the user selects 2, will open json file
                    } else if (type.equalsIgnoreCase("2")){
                        System.out.println("Enter name of file:");
                        fname = sc.nextLine();
                        ArrayList<UsernamesAndPasswords> userandpass =
                                upc.readUsernamesAndPasswordsFromJSON(fname);
                        for(UsernamesAndPasswords u:userandpass){
                            list1.addElement(u.getWebsite());
                            list1.addElement(u.getUsername());
                            list1.addElement(u.getPassword());
                        }
                        System.out.println("Done.");
                        // if the user is can't read instructions, yell at them
                    } else {
                        System.out.println("Try again!!");
                    }
                }catch (Exception ex){
                    System.out.println("Just read, it's not hard!");
                }
            }});

        // displays help
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upc.displayHelp();
            }
        });

        // saves the file
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String outFileName;
                String website, username, password;
                ArrayList<UsernamesAndPasswords> upInfo = new
                        ArrayList<UsernamesAndPasswords>();
                try{
                    // stores the list into an ArrayList
                    for (int i = 0; i < list1.getSize() - 2; i++){
                        website = list1.getElementAt(i);
                        i+=1;
                        username = list1.getElementAt(i);
                        i+=1;
                        password = list1.getElementAt(i);
                        upInfo.add(new UsernamesAndPasswords(website, username, password));
                    }

                    // creates scanner to get input from user
                    Scanner sc = new Scanner(System.in);
                    String type;
                    System.out.println("Enter 1 for text file or 2 for JSON:");
                    type = sc.nextLine();

                    if(type.equalsIgnoreCase("1")||type.equalsIgnoreCase("2")){
                        System.out.println("Enter the name of the file to write to: ");
                        outFileName = sc.nextLine();
                        // if user types 1, saves list to text file
                        if (type.equalsIgnoreCase("1")){
                            if(upc.saveToTextFile(upInfo, outFileName) == true){
                                System.out.println("Success.");
                            } else{
                                System.out.println("Failure.");
                            }
                        // if user types 2, saves list to json file
                        } else if (type.equalsIgnoreCase("2")) {
                            if(upc.writeUandPToJSON(outFileName, upInfo) == true){
                                System.out.println("Success.");
                            } else{
                                System.out.println("Failure.");
                            }
                        // again, yell at the user for not reading
                        } else{
                            System.out.println("You did not read the instructions!");
                        }
                    } else{
                        System.out.println("JUST FOLLOW THE INSTRUCTIONS!");
                    }

                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "There was" +
                            " an error saving the file");
                }
            }});

        // gets website username and password and stores into list
        ArrayList<String> infoList = new ArrayList<>();
        for (int i = 0; i < list1.getSize(); i ++){
            infoList.add(list1.getElementAt(i));
        }
    }

    public void actionPerformed(ActionEvent e){ }

}


class UsernamesAndPasswordsApp{
    public static void main(String[] args){
        System.out.println("Loading UsernamesAndPasswords...\n");
        UandPFrame upFrame = new UandPFrame();
        upFrame.setVisible(true);
    }
}