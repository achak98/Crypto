/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.crypt;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import javax.swing.JFrame;
import java.math.BigInteger;
import java.nio.charset.Charset;


/**
 *
 * @author abhic
 */
public class CryptForm extends javax.swing.JFrame {

    /**
     * Creates new form Login_Form
     */
    public CryptForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

    public void caesarC(boolean directionFlag){
        String pt="",ct="";
        int key;
        key=Integer.parseInt(keyF.getText());
        if(directionFlag==true){
            pt=ptF.getText().toUpperCase();
            for(int i=0;i<pt.length();i++){
                char a='0';
                a=pt.charAt(i);
                int x;
                x=(((int)(a)-65+key)%26)+65;
                ct = ct+((char)(x));
            }
        ctF.setText(ct);       
        }
        if(directionFlag==false){
            ct=ctF.getText().toUpperCase();
            for(int i=0;i<ct.length();i++){
                char a='0';
                a=ct.charAt(i);
                int x;
                x=(((int)(a)-65-(key%26)));
                if(x<0)
                    x+=26;
                x+=65;
                pt = pt+((char)(x));
            }
        ptF.setText(pt.toLowerCase());
        }
    
    }
    public void playFairC(boolean directionFlag){
        PlayfairCipherAlgo x = new PlayfairCipherAlgo();
        String key = keyF.getText();
        x.createTable(key, true);
        
        if(directionFlag==true){
            String p = ptF.getText();
            String c = x.encode(x.prepareText(p, true));
            ctF.setText(c);
        }
        else{
            String c = ctF.getText();
            String p = x.decode(c);
            ptF.setText(p);
        }
 
       
    }
    public void vernamC(boolean directionFlag){
        String key=keyF.getText();
        
      
        
        if (directionFlag==true){
            String pt=ptF.getText();
            while(key.length()<pt.length()){
                key=key+key;
            }
            BigInteger p = new BigInteger(pt.getBytes(Charset.forName("ascii")));
            BigInteger k = new BigInteger(key.getBytes(Charset.forName("ascii")));
            BigInteger c = p.xor(k);
            String ct = new String(c.toByteArray(), Charset.forName("ascii"));
            ctF.setText(ct);
        }
        else{
            String ct=ctF.getText();
            while(key.length()<ct.length()){
                key=key+key;
            }
            BigInteger c = new BigInteger(ct.getBytes(Charset.forName("ascii")));
            BigInteger k = new BigInteger(key.getBytes(Charset.forName("ascii")));
            BigInteger p = c.xor(k);
            String pt = new String(p.toByteArray(), Charset.forName("ascii"));
            ptF.setText(pt);
        }
    }
    public void hillC(boolean directionFlag){
        HillCipherAlgo x = new HillCipherAlgo();
        String key = keyF.getText();
        if(directionFlag==true){
            String p = ptF.getText();
            String c = x.encrypt(p,key);
            ctF.setText(c);
        }
        
       else{
        String c = ctF.getText();
        String p = x.decrypt(c,key);
        ptF.setText(p);
        }
    }
    public void vigenereC(boolean directionFlag){
        int z=19;
        String key=keyF.getText();
        String pt=ptF.getText();
        String ct=ctF.getText();
              
        if (directionFlag==true){
            char ptA [] = pt.toCharArray();
            char ctA [] = new char [pt.length()];
            while(key.length()<pt.length()){
                key=key.concat(key);
            }
            char keyA [] = key.toCharArray();
            System.out.println(key);

            for(int i=0;i<ptA.length;i++){
                int a = (ptA[i]);
                int b = (keyA[i]);
                int c =(a+b-z)%126;
                ctA[i]=(char) (c);
                
            }
          ctF.setText(new String(ctA));
        }
        else{
            char ctA [] = ct.toCharArray();
            char ptA [] = new char [ct.length()];
            while(key.length()<ctA.length){
                key=key.concat(key);
            }
            char keyA [] = key.toCharArray();

            for(int i=0;i<ctA.length;i++){
                int a = (ctA[i]);
                int b = (keyA[i]);
                int k = a-b+z;
                while(k<0)
                    k+=126;
                ptA[i]=(char) (k);
            }
            ptF.setText(new String(ptA));
        
        }
    }
    public void autokeyC(boolean directionFlag){
        String key = keyF.getText();
        char [] keyA = key.toCharArray();
        if(directionFlag==true){
            String pt = ptF.getText();
            char [] ptA = pt.toCharArray();
            char [] ctA = new char[pt.length()];
            int j=0;
            for(int i=0;i<pt.length();i++){
                if(i<keyA.length)
                    ctA[i]=(char) (((int)(ptA[i]))+((int)(keyA[i])));
                    
                else{
                    ctA[i]=(char) (((int)(ptA[i]))+((int)(ptA[j])));
                    j++;
                }
            }
            ctF.setText(new String(ctA));
        }
        else{
            String ct = ctF.getText();
            char [] ctA = ct.toCharArray();
            char [] ptA = new char[ctA.length];
            char [] keyA2 = new char[ctA.length];
            System.arraycopy(keyA, 0, keyA2, 0, keyA.length);
            int j,k=keyA.length;
            j=k;
            for(int i=0;i<ctA.length;i++){
                if(i<keyA.length){
                    int a = ctA[i];
                    int b = keyA2[i];
                    ptA[i]=(char) (a-b);
                    if(k<ctA.length){
                        keyA2[k]=ptA[i];
                        k++;       
                    }
                }
                else{
                    int a = ctA[i];
                    int b = keyA2[j];
                    ptA[i]=(char) (a-b);
                    if(k<ctA.length){
                        keyA2[k]=ptA[i];
                        k++;
                    }
                    j++;
                }
            }
            ptF.setText(new String(ptA));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        J_Label_MINIMIZE = new javax.swing.JLabel();
        J_Label_CLOSE = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        encrypt = new java.awt.Button();
        decrypt = new java.awt.Button();
        ptF = new javax.swing.JTextField();
        ctF = new javax.swing.JTextField();
        caesar = new javax.swing.JRadioButton();
        playfair = new javax.swing.JRadioButton();
        vigenere = new javax.swing.JRadioButton();
        hill = new javax.swing.JRadioButton();
        vernam = new javax.swing.JRadioButton();
        autokey = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        keyF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 102, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 5));

        jLabel2.setFont(new java.awt.Font("Myriad Pro Black SemiCond", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("E CRYPT");

        J_Label_MINIMIZE.setFont(new java.awt.Font("Myriad Pro Black SemiCond", 1, 36)); // NOI18N
        J_Label_MINIMIZE.setForeground(new java.awt.Color(255, 255, 255));
        J_Label_MINIMIZE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        J_Label_MINIMIZE.setText("-");
        J_Label_MINIMIZE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                J_Label_MINIMIZEMouseClicked(evt);
            }
        });

        J_Label_CLOSE.setFont(new java.awt.Font("Myriad Pro Black SemiCond", 1, 36)); // NOI18N
        J_Label_CLOSE.setForeground(new java.awt.Color(255, 255, 255));
        J_Label_CLOSE.setText("X");
        J_Label_CLOSE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                J_Label_CLOSEMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(J_Label_MINIMIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(J_Label_CLOSE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(J_Label_MINIMIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(J_Label_CLOSE)))
                .addGap(14, 14, 14))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 5));

        jLabel1.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Cipher Text:");

        jLabel5.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Plain Text:");

        encrypt.setBackground(new java.awt.Color(0, 51, 51));
        encrypt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        encrypt.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        encrypt.setForeground(new java.awt.Color(153, 153, 153));
        encrypt.setLabel("Encrypt");
        encrypt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                encryptMouseClicked(evt);
            }
        });
        encrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptActionPerformed(evt);
            }
        });

        decrypt.setActionCommand("Decrypt");
        decrypt.setBackground(new java.awt.Color(0, 51, 51));
        decrypt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        decrypt.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        decrypt.setForeground(new java.awt.Color(153, 153, 153));
        decrypt.setLabel("Decrypt");
        decrypt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decryptMouseClicked(evt);
            }
        });
        decrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptActionPerformed(evt);
            }
        });

        caesar.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(caesar);
        caesar.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        caesar.setForeground(new java.awt.Color(102, 102, 102));
        caesar.setText("Caesar Cipher");

        playfair.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(playfair);
        playfair.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        playfair.setForeground(new java.awt.Color(102, 102, 102));
        playfair.setText("PlayFair Cipher");
        playfair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playfairActionPerformed(evt);
            }
        });

        vigenere.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(vigenere);
        vigenere.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        vigenere.setForeground(new java.awt.Color(102, 102, 102));
        vigenere.setText("Vigenere Cipher");

        hill.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(hill);
        hill.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        hill.setForeground(new java.awt.Color(102, 102, 102));
        hill.setText("Hill Cipher");
        hill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hillActionPerformed(evt);
            }
        });

        vernam.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(vernam);
        vernam.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        vernam.setForeground(new java.awt.Color(102, 102, 102));
        vernam.setText("Vernam Cipher");

        autokey.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup1.add(autokey);
        autokey.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        autokey.setForeground(new java.awt.Color(102, 102, 102));
        autokey.setText("Autokey Cipher");

        jLabel6.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Key:");

        keyF.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        keyF.setForeground(new java.awt.Color(102, 102, 102));
        keyF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        keyF.setText("Enter Key here");
        keyF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                keyFFocusGained(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Cipher Type:");

        jLabel3.setFont(new java.awt.Font("Futura Md BT", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Character Limit 500");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(vigenere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(292, 292, 292)
                        .addComponent(autokey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(182, 182, 182))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(caesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(153, 153, 153)
                        .addComponent(playfair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(441, 441, 441))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(742, 742, 742))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(keyF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ptF)
                                    .addComponent(encrypt, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
                                .addGap(88, 88, 88)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ctF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(decrypt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(vernam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(89, 89, 89)
                                        .addComponent(hill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(8, 8, 8)))))
                        .addGap(66, 66, 66))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptF, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctF, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(keyF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(caesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vernam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playfair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vigenere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(autokey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(decrypt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void J_Label_CLOSEMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        System.exit(0);
    }                                          

    private void J_Label_MINIMIZEMouseClicked(java.awt.event.MouseEvent evt) {                                              
        // TODO add your handling code here:
        this.setState(JFrame.ICONIFIED);
    }                                             

    private void encryptActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
        boolean directionFlag=true;
        if(caesar.isSelected())
            caesarC(directionFlag);
        if(playfair.isSelected())
            playFairC(directionFlag);
        if(vernam.isSelected())
            vernamC(directionFlag);
        if(vigenere.isSelected())
            vigenereC(directionFlag);
        if(autokey.isSelected())
            autokeyC(directionFlag);
        if(hill.isSelected())
            hillC(directionFlag);
    }                                       

    @SuppressWarnings("empty-statement")
    private void encryptMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
      
    }                                    

    private void decryptMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void decryptActionPerformed(java.awt.event.ActionEvent evt) {                                        
        boolean directionFlag=false;
        if(caesar.isSelected())
            caesarC(directionFlag);   
        if(playfair.isSelected())
            playFairC(directionFlag);
        if(vernam.isSelected())
            vernamC(directionFlag);
        if(vigenere.isSelected())
            vigenereC(directionFlag);
        if(autokey.isSelected())
            autokeyC(directionFlag);
        if(hill.isSelected())
            hillC(directionFlag);
    }                                       

    private void playfairActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void hillActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void keyFFocusGained(java.awt.event.FocusEvent evt) {                                 
    keyF.setText("");        // TODO add your handling code here:
    }                                

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CryptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CryptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CryptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CryptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CryptForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel J_Label_CLOSE;
    private javax.swing.JLabel J_Label_MINIMIZE;
    private javax.swing.JRadioButton autokey;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton caesar;
    private javax.swing.JTextField ctF;
    private java.awt.Button decrypt;
    private java.awt.Button encrypt;
    private javax.swing.JRadioButton hill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField keyF;
    private javax.swing.JRadioButton playfair;
    private javax.swing.JTextField ptF;
    private javax.swing.JRadioButton vernam;
    private javax.swing.JRadioButton vigenere;
    // End of variables declaration                   

    private void elseif(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
