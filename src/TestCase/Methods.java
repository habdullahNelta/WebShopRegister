package TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Methods extends JFrame implements MethodsInterface, ActionListener {


    private int AnzahlTest;
    private JButton Eingabe;
    private JButton EXIT;
    private JTextField AnzahltestField;
    private JLabel Titel;
    private WebDriver obj;
    private String Emailt[];
    private String PassWordt[];
    private JRadioButton LoginAndRegister;
    private JRadioButton NurRegister;

    public Methods() {
        super();
    }

    public int getAnzahlTest() {
        return AnzahlTest;
    }

    public void setAnzahlTest(int anzahlTest) {
        AnzahlTest = anzahlTest;
    }


    // function to generate a random string of length n
    public String RandomString(int length) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                //  + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    // Register test
    @Override
    public void Register() {
        Emailt = new String[getAnzahlTest() + 1];
        PassWordt = new String[getAnzahlTest() + 1];
        for (int i = 1; i <= getAnzahlTest(); i++) {
            System.out.println("TestNummer: " + i);

            //click register
            WebElement RegistreButton = obj.findElement(By.className("ico-register"));
            RegistreButton.click();

            //radio button male
            WebElement MaleButton = obj.findElement(By.id("gender-male"));
            MaleButton.click();

            //First name
            String FirstName1 = RandomString(5);
            WebElement FirstName = obj.findElement(By.id("FirstName"));
            FirstName.sendKeys(FirstName1);
            System.out.println("FirstName " + i + ":" + FirstName1);

            //LastName
            String LastName1 = RandomString(5);
            WebElement LastName = obj.findElement(By.id("LastName"));
            LastName.sendKeys(LastName1);
            System.out.println("LastName " + i + ":" + LastName1);

            //Email
            try {
                String Email1 = RandomString(5);
                String Email2 = RandomString(5);
                Emailt[i] = Email1 + "@" + Email2 + ".de";
                WebElement Email = obj.findElement(By.id("Email"));
                Email.sendKeys(Emailt[i]);
                System.out.println("Email " + i + ":" + Email1 + "@" + Email2 + ".de");
                // System.out.println("Hallo"+Emailt[i]);
            } catch (Exception e) {
                DialogTest("login Fehler");
            }
            //PassWord
            PassWordt[i] = RandomString(8);
            WebElement Password = obj.findElement(By.id("Password"));
            Password.sendKeys(PassWordt[i]);
            System.out.println("Password " + i + ":" + PassWordt[i]);

            //ConfirmPassword
            WebElement ConfirmPassword = obj.findElement(By.id("ConfirmPassword"));
            ConfirmPassword.sendKeys(PassWordt[i]);
            System.out.println("ConfirmPassword " + i + ":" + PassWordt[i]);
            try {
                //register-button
                WebElement RegisterConfirm = obj.findElement(By.id("register-button"));
                RegisterConfirm.click();
            } catch (Exception e) {
                DialogTest("register Fehler");
            }
            System.out.println("Test:" + i + " erfolgreich durchgeführt!");
            System.out.println("");

            //logout
            if (getAnzahlTest() >= 1) {
                WebElement logout = obj.findElement(By.className("ico-logout"));
                logout.click();
            }
            //browser schliessen
            if (getAnzahlTest() == i) {
                //obj.quit();
            }
        }
    }
    // pause durch thread time in sec
    public void Pause(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Browser() {
        try{
        //   private WebDriver obj;
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        obj = new ChromeDriver();
        obj.get("http://demowebshop.tricentis.com");
        } catch (Exception e){
            DialogTest("chromedriver.exe soll in 'C:\\' eingefügt werden");
        }

    }

    @Override
    public void Login() {
        for (int i = 1; i <= getAnzahlTest(); i++) {
            try {
                WebElement login = obj.findElement(By.className("ico-login"));
                login.click();
            } catch (Exception e) {
                DialogTest("login Fehler");
            }

            try {
                WebElement EmailLogin = obj.findElement(By.id("Email"));
                EmailLogin.sendKeys(Emailt[i]);//
            } catch (Exception e) {
                DialogTest("Email Fehler");
            }
            WebElement PassWordLogin = obj.findElement(By.id("Password"));
            PassWordLogin.sendKeys(PassWordt[i]);//
            //loginButton
            WebElement loginButton = obj.findElement(By.className("login-button"));
            loginButton.click();
            //logoutButton
            WebElement logout = obj.findElement(By.className("ico-logout"));
            logout.click();
        }
    }

    //////////GUI
    @Override
    public void DialogTest(String Text) {

        String backupDir = "test";
        // create a jframe
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frame, Text);
    }

    @Override
    public void GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Titel = new JLabel("Bitte Anzahltest Eingeben :");

        AnzahltestField = new JTextField(15);
        AnzahltestField.addActionListener(this);
        Eingabe = new JButton("OK");
        Eingabe.addActionListener(this);
        EXIT = new JButton("EXIT");
        EXIT.addActionListener(this);
        Eingabe.setToolTipText("Eingabe bestätigen");

        LoginAndRegister = new JRadioButton("Login und Register", true);
        NurRegister = new JRadioButton("Nur Register");
        ButtonGroup bg=new ButtonGroup();
        bg.add(LoginAndRegister);
        bg.add(NurRegister);
        // Layout-Style defined: 2 columns, 10p distance
        setLayout(new GridLayout(3, 2, 5, 5));
        setMinimumSize(new Dimension(400, 150));

        add(LoginAndRegister);
        add(NurRegister);
        add(Titel);
        add(AnzahltestField);
        //add(new JLabel(""));
        add(Eingabe);
        add(EXIT);
        setTitle("Register Test");
        setResizable(false);
        //pack(); // organise all the elements
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Eingabe) {
            setAnzahlTest(Integer.valueOf(AnzahltestField.getText()));
            Browser();
            Pause(1);
            //aufruf öffnen browser und task register test
            if (LoginAndRegister.isSelected()) {
                Register();
                Pause(1);
                Login();
                DialogTest("Es wurde  " + AnzahlTest + " Testfälle durchgeführt.");
                System.out.print("vielen Dank für den Test");
                //obj.quit();
                // System.out.println("LoginAndRegister is Selected() ");
            } else if (NurRegister.isSelected()) {
                Register();
                DialogTest("Es wurde  " + AnzahlTest + " Testfälle durchgeführt.");
                System.out.print("vielen Dank für den Test");
                //obj.quit();
                // System.out.println("Nur Register is Selected() ");
            }
        }
        if (e.getSource() == EXIT) {
            System.exit(0);
        }
        obj.quit();
    }
}
