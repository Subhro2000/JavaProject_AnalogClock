/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.main;

import clock.panel.analog.RomanClockPanel;
import clock.tools.CenteredFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Subhro Ghosh
 */
public class RomanClock
{
    
    public static void main(String[] args)
    {
        JFrame frm = new CenteredFrame("Roman Clock");
        JPanel panel = new RomanClockPanel();
        frm.add(panel);
        frm.setVisible(true);
        Timer timer = new Timer(900, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panel.repaint();
            }
        });
        timer.start();
    }
}
