/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.tools;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Subhro Ghosh
 */
public class ColorTable
{
    public  Color[] colors;
    
    public static ColorTable buildTable()
    {
        ColorTable ct = new ColorTable();
        ct.colors = readColors("Big Color Table.txt", true, 0);
        return ct;
    }
    
    public static Color[] readColors(String fileName, boolean hasHeading, int colNo) // Clom number containing hex colors
    {
        FileInputStream fin = null;
        Color colors[] = new Color[0];
        try
        {
            fin = new FileInputStream(fileName);
        } catch (FileNotFoundException ex)
        {
        }
        if(fin!=null){
        Scanner sc = new Scanner(fin);
        if(hasHeading)
            sc.nextLine(); //Skip the heading
        
        ArrayList<Color> vl = new ArrayList();
        
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String fields[] = line.split("\t");
            vl.add(new Color (Integer.parseInt(fields[colNo].substring(1), 16)));
        }
        
        sc.close();
        colors = new Color[vl.size()];
            for (int i = 0; i < colors.length; i++)
            {
                colors[i] = vl.get(i);
            }
        }
        
        return colors;
    }   
}
