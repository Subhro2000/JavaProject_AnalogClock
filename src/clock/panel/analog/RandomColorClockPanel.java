/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.panel.analog;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Subhro Ghosh
 */
public class RandomColorClockPanel extends ColoredClockPanel
{
    
    public RandomColorClockPanel(Color[] colors)
    {
        super(colors);
    }

    @Override
    public void drawColoredDial(Graphics g)
    {
        int startAngle = 270;
        int arcAngle = 30;
        for(int i=0; i<12; i++)
        {
            g.setColor(genColor());
            
            g.fillArc(x, y, side, side, -startAngle,-arcAngle);
            startAngle+=arcAngle;
        }
    }
    
    private Color genColor()
    {
        
         int no = (int)Math.floor(Math.random()*colors.length);
         return colors[no];
         
    }
    
}
