/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.panel.analog;

import clock.tools.SquaredPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author Subhro Ghosh
 */
public class ColoredClockPanel extends SquaredPanel
{
    protected Color[] colors;
    private Color[] sliceColor;
    private final int SLICE;
    
    public ColoredClockPanel(Color[] colors)
    {
        this.colors = colors;
        this.SLICE = 12;
        this.sliceColor = genColors(SLICE);
    }
    
    private  Color[] genColors(int n)
    {
        int nums[] = new int[n];
        Color cols[] = new Color[n];
        
        Random rand = new Random();
        int r;
        for(int i=0; i<n; i++)
        {
            while(true)
            {
                r = rand.nextInt(colors.length);
                int j;
                for(j=0; j<i; j++)
                {
                    if(nums[j]==r)
                        break;
                }
                if(j==i)
                    break;
            }
            nums[i] = r;
            cols[i] = colors[r];
        }
        System.out.println(Arrays.toString(nums));
        
        return cols;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        GregorianCalendar time = new GregorianCalendar();
        int sec = time.get(Calendar.SECOND);
        int min = time.get(Calendar.MINUTE);
        int hr = time.get(Calendar.HOUR);
        
        int gap = side*10/100; // gap between inner dial and outer dial
        int x1 = x+gap;
        int y1 = y+gap;
        int side1 = side-(gap*2);
        
        int l = side/2;
        int x0 = x+l;
        int y0 = y+l;
        
        drawColoredDial(g);
        drawNumber(g, x0, y0, l, gap);                
        drawHand(g, x0, y0, l, sec, min, hr);
        
        g.setColor(Color.black);
        g.drawOval(x1, y1, side1, side1); // drawing the inner dial
        
        // drawing middle big dot
        int dia = (int) (side*.02);
        g.setColor(Color.green);
        g.fillOval(x0-dia/2, y0-dia/2, dia, dia);
    }
    
    
    public void drawColoredDial(Graphics g)
    {

        int startAngle = 270;
        int arcAngle = 30;
        for(int i=0; i<12; i++)
        {
            g.setColor(sliceColor[i]);
            
            g.fillArc(x, y, side, side, -startAngle,-arcAngle);
            startAngle+=arcAngle;
        }
    }
    
    private void drawNumber(Graphics g, int x0, int y0, int l, int gap)
    {
        
        g.setColor(Color.black);
        int len = l-gap/2;
        int fontSize = side*5/100;
        int smallDot = side*1/100;
        
        Font fnt = new Font("Times New Roman", Font.BOLD+Font.ITALIC, fontSize);
        double rad;
        for(int i=0; i<=360; i+=6)
        {
            rad = Math.toRadians(-90+i);
            int x2 = (int)(x0+len*Math.cos(rad));
            int y2 = (int)(y0+len*Math.sin(rad));
            
            if(i!=0)
            {
                if(i%30==0)
                {
                    String a = i/30+"";
                    g.setFont(fnt);
                    FontMetrics fm = g.getFontMetrics();
                    int ht = fm.getHeight();
                    int wd = fm.stringWidth(a);
                    g.drawString(a, x2-wd/2, y2+ht/3);
//                    g.fillOval(x2-wd/2, y2-ht/2, fontSize, fontSize);
                    
                }
                else
                {
                    g.fillOval(x2-smallDot/2, y2-smallDot/2, smallDot, smallDot);
                }
            }
        }
    }

    private void drawHand(Graphics g, int x0, int y0, int l,int sec, int min, int hr)
    {

        double rad, rad2;
        
        // drawing hour hand
        int hangle = 30*hr+(min/2);
        int hrlength = l*40/100;
        rad = Math.toRadians(hangle-90);
        int xh = (int)(x0+hrlength*Math.cos(rad));
        int yh = (int)(y0+hrlength*Math.sin(rad));

        // drawing hour tail
        int hrtaillength=hrlength*30/100;
        rad2 = Math.toRadians(hangle+90);
        int xh2 = (int)(x0+hrtaillength*Math.cos(rad2));
        int yh2 = (int)(y0+hrtaillength*Math.sin(rad2));
        
        // drawing hour hand polygon
        int htlen = hrtaillength/3;
        double radh = Math.toRadians(hangle+180);
        int xht1 = (int)(x0+htlen*Math.cos(radh));
        int yht1 = (int)(y0+htlen*Math.sin(radh));
        radh = Math.toRadians(hangle);
        int xht2 = (int)(x0+htlen*Math.cos(radh));
        int yht2 = (int)(y0+htlen*Math.sin(radh));

        g.setColor(Color.black);
        g.fillPolygon(new int[]{xh2,xht1,xh,xht2,xh2},new int[]{yh2,yht1,yh,yht2,yh2} , 5);
        
        g.setColor(Color.yellow);
        g.drawLine(x0, y0, xh, yh);
        g.drawLine(x0, y0, xh2, yh2);
        g.drawLine(x0, y0, xht1, yht1);
        g.drawLine(x0, y0, xht2, yht2);
        g.drawPolygon(new int[]{xh2,xht1,xh,xht2,xh2},new int[]{yh2,yht1,yh,yht2,yh2} , 5);
        
        
        // drawing minute hand
        g.setColor(Color.green);
        int mangle = 6*min+(sec/10);
        int minlength = l*70/100;
        rad = Math.toRadians(mangle-90);
        int xm = (int)(x0+minlength*Math.cos(rad));
        int ym = (int)(y0+minlength*Math.sin(rad));
        
        // drawing min tail
        int mintaillength=hrtaillength;
        rad2 = Math.toRadians(mangle+90);
        int xm2 = (int)(x0+mintaillength*Math.cos(rad2));
        int ym2 = (int)(y0+mintaillength*Math.sin(rad2));
        
        // drawing minute hand polygon
        int mtlen = mintaillength/3;
        double radt = Math.toRadians(mangle+180);
        int xmt1 = (int)(x0+mtlen*Math.cos(radt));
        int ymt1 = (int)(y0+mtlen*Math.sin(radt));
        radt = Math.toRadians(mangle);
        int xmt2 = (int)(x0+mtlen*Math.cos(radt));
        int ymt2 = (int)(y0+mtlen*Math.sin(radt));
        
        g.setColor(Color.black);
        g.fillPolygon(new int[]{xm2,xmt1,xm,xmt2,xm2},new int[]{ym2,ymt1,ym,ymt2,ym2} , 5);
        
        g.setColor(Color.yellow);
        g.drawLine(x0, y0, xm, ym);
        g.drawLine(x0, y0, xm2, ym2);
        g.drawLine(x0, y0, xmt1, ymt1);
        g.drawLine(x0, y0, xmt2, ymt2);
        g.drawPolygon(new int[]{xm2,xmt1,xm,xmt2,xm2},new int[]{ym2,ymt1,ym,ymt2,ym2} , 5);
        
        
        // drawing second hand
        g.setColor(Color.red);
        int sangle = 6*sec;
        int seclength = l*90/100;
        rad = Math.toRadians(sangle-90);
        int xs = (int)(x0+seclength*Math.cos(rad));
        int ys = (int)(y0+seclength*Math.sin(rad));
        g.drawLine(x0, y0, xs, ys);
        
        // drawing sec tail
        int sectaillength = seclength*20/100;
        rad2 = Math.toRadians(sangle+90);
        int xs2 = (int)(x0+sectaillength*Math.cos(rad2));
        int ys2 = (int)(y0+sectaillength*Math.sin(rad2));
        g.drawLine(x0, y0, xs2, ys2);
        
    }    
}
