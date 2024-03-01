/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.main;

import clock.panel.analog.ColoredClockPanel;
import clock.tools.CenteredFrame;
import clock.tools.ColorTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Subhro Ghosh
 */
public class Clock
{
    public static void main(String[] args)
    {
        ColorTable colTable = ColorTable.buildTable();
        JFrame frm = new CenteredFrame("Clock");
        
//        JPanel panel = new RomanClockPanel();
//        JPanel panel = new RandomColorClockPanel(colTable.colors);
        JPanel panel = new ColoredClockPanel(colTable.colors);
        frm.add(panel);
        Timer timer = new Timer(900, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                panel.repaint();
            }
        });
        timer.start();
        frm.setVisible(true);
    }
}
