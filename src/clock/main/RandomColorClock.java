/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.main;

import clock.panel.analog.RandomColorClockPanel;
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
public class RandomColorClock
{
    public static void main(String[] args)
    {
        ColorTable colTable = ColorTable.buildTable();
        JFrame frm = new CenteredFrame("Changing Color Clock");
        JPanel panel = new RandomColorClockPanel(colTable.colors);
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
