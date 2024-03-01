/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.tools;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Subhro Ghosh
 */
public class CenteredFrame extends JFrame
{

    public CenteredFrame(String title)
    {
        this.setTitle(title);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int wd = d.width;
        int ht = d.height;
        
        this.setSize(wd/3 , ht/2);
        int x = ( wd-this.getWidth())/2;
        int y = (ht - this.getHeight())/2;
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
