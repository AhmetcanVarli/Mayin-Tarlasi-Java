package MayinTarlasiJava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Ekran implements ActionListener, MouseListener {
    
    private JFrame ekran = null;
    private JButton smiley = new JButton("");
    private JPanel Panel1 = new JPanel();
    private JPanel ustPanel = new JPanel();
    
    ImageIcon smileyImageIcon = null;
    ImageIcon smileyCryIcon = null;
    ImageIcon butonIcon = null;
    ImageIcon pitIcon = null;
    ImageIcon lossIcon = null;
    ImageIcon birIcon = null;
    ImageIcon ikiIcon = null;
    ImageIcon ucIcon = null;
    ImageIcon dortIcon = null;
    ImageIcon besIcon = null;
    
    public Ekran() {
        ekran = new JFrame("Mayın Tarlası");
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ekran.setVisible(true);
        ekran.setResizable(false);
        ekran.setJMenuBar(menubar());
        ekran.setLocationRelativeTo(null);
        IconEkle();
        
        Panel1.setLayout(new BorderLayout());
        smiley.setPreferredSize(new Dimension(25, 25));
        smiley.setIcon(smileyImageIcon);
        ustPanel.add(smiley);
        Panel1.add(ustPanel, BorderLayout.NORTH);
        smiley.addActionListener(this);
        smiley.addMouseListener(this);
        ButonDuzen();
        ekran.add(Panel1);
        ekran.pack();
    }
    
    public void IconEkle() {
        
        smileyImageIcon = IconAl("Iconlar/smiley.png"); 
        smileyCryIcon = IconAl("Iconlar/smileyCry.png");
        butonIcon = IconAl("Iconlar/t.png");
        pitIcon = IconAl("Iconlar/pit.png");
        lossIcon = IconAl("Iconlar/loss.png");
        birIcon = IconAl("Iconlar/1.png");
        ikiIcon = IconAl("Iconlar/2.png");
        ucIcon = IconAl("Iconlar/3.png");
        dortIcon = IconAl("Iconlar/4.png");
        besIcon = IconAl("Iconlar/5.png");        
    }
    
    public JMenuBar menubar() {
        JMenuBar mBar = new JMenuBar();
        JMenu Oyun = new JMenu("Oyun");
        
        
        final JMenuItem miYeni = new JMenuItem("Yeni Oyun");
        final JMenuItem miBaslangıc = new JMenuItem("Başlangıç");
        final JMenuItem miOrta = new JMenuItem("Orta");
        final JMenuItem miZor = new JMenuItem("Zor");
        final JMenuItem miCıkıs = new JMenuItem("Çıkış");
        
        Oyun.add(miYeni);
        Oyun.add(miBaslangıc);
        Oyun.add(miOrta);
        Oyun.add(miZor);
        Oyun.add(miCıkıs);        
        
        ActionListener MenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (miYeni == e.getSource()) {
                    butonwdth = 9;
                    butonhgt = 9;
                    mines = 10;
                    Sıfırla();
                }
                if (miBaslangıc == e.getSource()) {
                    butonwdth = 9;
                    butonhgt = 9;
                    mines = 10;
                    Sıfırla();
                }
                if (miOrta == e.getSource()) {
                    butonwdth = 16;
                    butonhgt = 16;
                    mines = 40;
                    Sıfırla();
                }
                if (miZor == e.getSource()) {
                    butonwdth = 16;
                    butonhgt = 30;
                    mines = 99;
                    Sıfırla();
                }
                if (miCıkıs == e.getSource()) {
                    if (ekran != null) {
                        ekran.dispose();
                    }
                    System.exit(0);
                }
                
            }
        };
        
        miYeni.addActionListener(MenuListener);
        miBaslangıc.addActionListener(MenuListener);
        miOrta.addActionListener(MenuListener);
        miZor.addActionListener(MenuListener);
        miCıkıs.addActionListener(MenuListener);
        mBar.add(Oyun);
        
        
        return mBar;        
    }
    
    private int butonwdth = 10;
    private int butonhgt = 10;
    private int mines = 8;
    
    int mayindizi[][];
    
    JButton buton[][];
    JPanel mayin = null;
    
    public void ButonDuzen() {
        mayindizi = new int[butonwdth][butonhgt];
        buton = new JButton[butonwdth][butonhgt];
        boolean baslangıc = true;
        if (mayin != null) {
            Panel1.remove(mayin);
            mayin = null;
            baslangıc = false;
        }
        mayin = new JPanel();
        mayin.setLayout(new GridLayout(butonwdth, butonhgt));
        
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                mayindizi[i][j] = 0;
                buton[i][j] = new JButton("");
                buton[i][j].setBackground(Color.WHITE);
                buton[i][j].setPreferredSize(new Dimension(16, 16));
                buton[i][j].addActionListener(this);
                buton[i][j].addMouseListener(this);
                mayin.add(buton[i][j]);
            }
        }
        mayin.setVisible(true);
        Panel1.add(mayin, BorderLayout.CENTER);
        if (baslangıc) {
            Mayinlar(buton);
        }
        ekran.pack();        
    }
    
    public void Sıfırla() {
        smiley.setIcon(smileyImageIcon);
        ButonDuzen();
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                mayindizi[i][j] = 0;
                buton[i][j].addActionListener(this);
                buton[i][j].addMouseListener(this);
                buton[i][j].setText("");
                buton[i][j].setBackground(Color.WHITE);
                
            }
        }
        Mayinlar(buton);
        System.out.println("");
        System.out.println("");
    }
    
    public void Mayinlar(JButton buton[][]) {
        int mayin[] = rnd(butonwdth, butonhgt, mines);
        int sayac = 1;
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                for (int k = 0; k < mayin.length && mayin[k] != 0; k++) {
                    if (sayac == mayin[k]) {
                        mayindizi[i][j] = 9;
                    }
                }
                sayac++;
            }
        }
        int kutusayac = 0;
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                kutusayac = 0;
                
                if (mayindizi[i][j] != 9) {
                    if (i > 0 && j > 0) {
                        if (mayindizi[i - 1][j - 1] == 9) {
                            kutusayac++;
                        }
                    }
                    if (i > 0) {
                        if (mayindizi[i - 1][j] == 9) {
                            kutusayac++;
                        }
                    }
                    if (i > 0 && j < butonhgt - 1) {
                        if (mayindizi[i - 1][j + 1] == 9) {
                            kutusayac++;
                        }
                    }
                    if (i < butonwdth - 1 && j > 0) {
                        if (mayindizi[i + 1][j - 1] == 9) {
                            kutusayac++;
                        }
                    }
                    if (i < butonwdth - 1) {
                        if (mayindizi[i + 1][j] == 9) {
                            kutusayac++;
                        }
                    }
                    if (i < butonwdth - 1 && j < butonhgt - 1) {
                        if (mayindizi[i + 1][j + 1] == 9) {
                            kutusayac++;
                        }
                    }
                    if (j > 0) {
                        if (mayindizi[i][j - 1] == 9) {
                            kutusayac++;
                        }
                    }
                    if (j < butonhgt - 1) {
                        if (mayindizi[i][j + 1] == 9) {
                            kutusayac++;
                        }
                    }
                    mayindizi[i][j] = kutusayac;
                }
                
            }
        }
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                System.out.print(" " + mayindizi[i][j]);
            }
            System.out.println("");
        }
        
    }
    
    public int[] rnd(int butonwdth, int butonhgt, int mayin) {
        Random rand = new Random();
        int rndMayin[] = new int[butonwdth * butonhgt];
        boolean in = false;
        int sayac = 0;
        while (sayac < mayin) {            
            int rndno = (int) ((butonwdth * butonhgt) * (rand.nextDouble())) + 1;
            in = false;
            for (int i = 0; i < sayac; i++) {
                if (rndMayin[i] == rndno) {
                    in = true;
                    break;
                }
            }
            if (!in) {
                rndMayin[sayac++] = rndno;
            }
        }
        return rndMayin;
        
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == smiley) {
            Sıfırla();
        } else {
            for (int i = 0; i < butonwdth; i++) {
                for (int j = 0; j < butonhgt; j++) {
                    if (buton[i][j] == e.getSource()) {
                        if (mayindizi[i][j] == 9) {
                            for (int k = 0; k < butonwdth; k++) {
                                for (int l = 0; l < butonhgt; l++) {
                                    if (mayindizi[k][l] == 9) {
                                        buton[k][l].setIcon(pitIcon);
                                    }
                                    buton[k][l].removeActionListener(this);
                                    buton[k][l].removeMouseListener(this);
                                }
                            }
                        }
                        if (mayindizi[i][j] == 1) {
                            buton[i][j].setIcon(birIcon);
                        }
                        if (mayindizi[i][j] == 2) {
                            buton[i][j].setIcon(ikiIcon);
                        }
                        if (mayindizi[i][j] == 3) {
                            buton[i][j].setIcon(ucIcon);
                        }
                        if (mayindizi[i][j] == 4) {
                            buton[i][j].setIcon(dortIcon);
                        }
                        if (mayindizi[i][j] == 5) {
                            buton[i][j].setIcon(besIcon);
                        }
                        if (mayindizi[i][j] == 0) {
                            BoslariBul(i, j);
                        }  
                    }
                }
            }
        }
    }
    
    public void BoslariBul(int kutuX, int kutuY) {
        int diziX[] = new int[(butonwdth) * (butonhgt)];
        int diziY[] = new int[(butonwdth) * (butonhgt)];
        int sayacBos = 0;
        
        for (int i = 0; i < ((butonwdth) * (butonhgt)); i++) {
            diziX[i] = -1;
            diziY[i] = -1;
        }
        diziX[sayacBos] = kutuX;
        diziY[sayacBos] = kutuY;
        sayacBos++;
        
        for (int i = 0; i < sayacBos; i++) {
            if (diziX[i] > 0) {
                int xxX = diziX[i] - 1;
                int yyY = diziY[i];
                if (mayindizi[xxX][yyY] == 0) {
                    if (!bul(diziX, diziY, sayacBos, xxX, yyY)) {
                        diziX[sayacBos] = xxX;
                        diziY[sayacBos] = yyY;
                        sayacBos++;
                    }
                }
            }
            if (diziX[i] < (butonwdth - 1)) {
                int xxX = diziX[i] + 1;
                int yyY = diziY[i];
                if (mayindizi[xxX][yyY] == 0) {
                    if (!bul(diziX, diziY, sayacBos, xxX, yyY)) {
                        diziX[sayacBos] = xxX;
                        diziY[sayacBos] = yyY;
                        sayacBos++;
                    }
                }
            }
            if (diziY[i] > 0) {
                int xxX = diziX[i];
                int yyY = diziY[i] - 1;
                if (mayindizi[xxX][yyY] == 0) {
                    if (!bul(diziX, diziY, sayacBos, xxX, yyY)) {
                        diziX[sayacBos] = xxX;
                        diziY[sayacBos] = yyY;
                        sayacBos++;
                    }
                }
            }
            if (diziY[i] < (butonhgt - 1)) {
                int xxX = diziX[i];
                int yyY = diziY[i] + 1;
                if (mayindizi[xxX][yyY] == 0) {
                    if (!bul(diziX, diziY, sayacBos, xxX, yyY)) {
                        diziX[sayacBos] = xxX;
                        diziY[sayacBos] = yyY;
                        sayacBos++;
                    }
                }
            }
        }
        for (int k = 0; k < sayacBos; k++) {
            buton[diziX[k]][diziY[k]].setBackground(new Color(200, 200, 250));
        }
        
    }
    
    public boolean bul(int[] diziX, int[] diziY, int sayacBos, int xxX, int yyY) {
        for (int j = 0; j < sayacBos; j++) {
            if ((diziX[j] == (xxX)) && (diziY[j] == (yyY))) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                if (buton[i][j] == e.getSource()) {
                    smiley.setIcon(smileyCryIcon);
                }
            }
        }
        if (e.getSource() == smiley) {
            smiley.setIcon(smileyCryIcon);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == smiley) {
            smiley.setIcon(smileyImageIcon);
        }
        for (int i = 0; i < butonwdth; i++) {
            for (int j = 0; j < butonhgt; j++) {
                if (buton[i][j] == e.getSource()) {
                    if (mayindizi[i][j] == 9) {
                        smiley.setIcon(lossIcon);
                    } else {
                        smiley.setIcon(smileyImageIcon);
                    }
                }
            }
        }
    }
    public ImageIcon IconAl(String imageString) {
        ImageIcon imageIcon = new ImageIcon(imageString);
        Image image = imageIcon.getImage();
        Image yeniImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(yeniImg);
        return imageIcon;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
