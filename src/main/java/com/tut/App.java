package com.tut;

import com.sun.glass.ui.Size;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class App
{

    public static String fetchdata (String c)throws Exception
    {
        StringBuffer bu = new StringBuffer();
        bu.append("<html>");
        String url = "https://countrymeters.info/en/" + c + "/";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("#population_clock");
       /* elements.forEach((e)->{
            System.out.println(e.html()); });  */
        //System.out.println(elements.select("tr").size());
        for (Element e : elements.select("tr"))
        {
            String counter = e.select("td.counter").text();
            String data = e.select("td.data_name").text();
            //System.out.println(counter);
            //System.out.println(data);
            //System.out.println(data + " : " + counter);
            bu.append(data).append(" : ").append(counter).append("<br>");


        };
        bu.append("</html>");
        return bu.toString();
    };


    public static void main( String[] args ) throws Exception
    {

//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter country name :");
//        String con = s.nextLine();
//        System.out.println(fetchdata(con));

        //GUI
        JFrame root = new JFrame("World Population Counter");
        root.setSize(800,800);
        Font f = new Font("Poppins",Font.BOLD,30 );


        //Text Field
        JTextField jtf = new JTextField();

        //label
        JLabel jl = new JLabel();
        jtf.setFont(f);
        jl.setFont(f);
        jtf.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setHorizontalAlignment(SwingConstants.CENTER);

        //button
        JButton button = new JButton("Get Data");

        button.addActionListener(event ->{
        try
        {
            String maindata=jtf.getText();
            String result = fetchdata(maindata);
            jl.setText(result);
        }
        catch (Exception e)
        {

            e.printStackTrace();

        }



        });




        button.setFont(f);
        root.setLayout(new BorderLayout());
        root.add(jtf,BorderLayout.NORTH);
        root.add(jl,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);

        root.setVisible(true);






    }
}
