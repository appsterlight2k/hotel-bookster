package com.appsterlight.$$$temporary;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "FileChooser", value = "/choose-files")
public class FileChooser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            String fileName = chooser.getSelectedFile().getName();
            request.setAttribute("photo_path", fileName);
            System.out.println("You chose to open this file: " + fileName);
        }

        response.sendRedirect("/photo.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        System.out.println("--> context path: " + context.getContextPath());
//        System.out.println("--> real path with: " + context.getRealPath(""));
//        System.out.println("--> real path with .: " + context.getRealPath("."));
//        System.out.println("--> real path with /: " + context.getRealPath("/"));

        String relativeWebPath = "/photos";
        String absoluteDiskPath = context.getRealPath(relativeWebPath);

        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setDirectory(absoluteDiskPath);
        dialog.setMultipleMode(true);

        dialog.setVisible(true);
        dialog.setAlwaysOnTop(true);
//        String file = dialog.getFile();
        File[] files = dialog.getFiles();
        String file;
        if (files.length > 0) {
            file = files[0].getName();
            System.out.println(file + " chosen.");
            request.setAttribute("photo_path", file);
        }

        dialog.dispose();

        System.out.println("getDirectory: ->  " +  dialog.getDirectory());
        System.out.println("getName: ->  " +  dialog.getName());
        System.out.println("getTitle: ->  " +  dialog.getTitle());
        System.out.println("getFiles: ->  " +  dialog.getFiles());
        System.out.println("isMultipleMode: ->  " +  dialog.isMultipleMode());
//        String path = request.getParameter("photo_path");


       /* realPath = getServletContext().getRealPath("/");
        String classPath = realPath + "WEB-INF/classes/" +
                MyClass.ServletContextUtil.class.getCanonicalName().replaceAll("\\.", "/") + ".java";*/


        request.getRequestDispatcher("/photo.jsp").forward(request, response);


    }
}
