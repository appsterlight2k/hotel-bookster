package com.appsterlight.$$$temporary;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.appsterlight.controller.context.AppContext;
import com.appsterlight.model.db.connection.DBManager;
import com.appsterlight.model.db.connection.MySQLDBManager;
import com.appsterlight.model.db.constants.Queries;
import com.appsterlight.model.db.dao.ApartmentDao;
import com.appsterlight.model.db.dao.ApartmentPhotosDao;
import com.appsterlight.model.db.dao.BookingDao;
import com.appsterlight.model.db.dao.UserDao;
import com.appsterlight.model.db.dao.mysql.MySqlDaoFactory;
import com.appsterlight.model.domain.Apartment;
import com.appsterlight.model.domain.Booking;
import com.appsterlight.model.domain.Role;
import com.appsterlight.model.domain.User;
import com.appsterlight.exception.DaoException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/test")
@Slf4j
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("TestServlet#doGet");

        ServletContext context = getServletContext();
        System.out.println(context);

        AppContext appContext = AppContext.getAppContext();

        DBManager dBManager = MySQLDBManager.getInstance();
        Connection con = dBManager.getConnection();

        MySqlDaoFactory factory = new MySqlDaoFactory(con);
        ApartmentDao apartmentDao = factory.getApartmentDao();

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = "16/08/2016";
        LocalDate localDate = LocalDate.parse(date, formatter); //convert String to LocalDate*/

        try {
            List<Apartment> list = apartmentDao.getAllFreeByGuestsNumber(1,
                    LocalDate.parse("2023-03-19"), LocalDate.parse("2023-03-20"));
            System.out.println("> size: " + list.size());
            System.out.println(list);

            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");


        } catch (DaoException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
//            throw new RuntimeException(e);
        }

//        try {
            /*UserDao userDao = factory.getUserDao();

            User u = new User(1L, "Ivan", "LastOf", "a@a.a", "+1234234234", "pass", Role.ROLE_USER.toString(), "");
            if (!userDao.getUserByEmail("a@a.a").isPresent()) {
                userDao.add(u);
            }
            boolean is_deleted = userDao.delete(Long.parseLong("1"));

            User u2 = userDao.get(2L).get();
            System.out.println("user2:  -> " + u2);

            u2.setFirstName("New-First-Name");
            boolean isUpdated = userDao.update(u2);
            System.out.println("- is updated: " + isUpdated);

            List<User> list = userDao.getAll();
            System.out.println("\n\n -------------- \n" + list);*/
/*

            //---[ test Apartment ]---
            Apartment app1 = new Apartment(0L, "455", 2, 1, 2, 1, "/photos/1.jpg", 99,  "Description");
            Apartment app2 = new Apartment(0L, "450", 2, 1, 2, 1, "", 99, "Description");
            ApartmentDao apartmentDao = factory.getApartmentDao();
            Long id_ap1 = apartmentDao.add(app1);
            Long id_ap2 = apartmentDao.add(app2); //stay

            apartmentDao.delete(id_ap1);

            app2.setPrice(1000);
            apartmentDao.update(app2);

            apartmentDao.get(4L);

            System.out.println("ALL -> /n/n" + apartmentDao.getAll());

            //---[ test Booking: ]---
            //1000$
            LocalDate dateIn = LocalDate.of(2023, 03, 12);
            LocalDate dateOut = LocalDate.of(2023, 03, 14);

            Timestamp time = Timestamp.valueOf("2023-02-15 14:19:26");
            Timestamp time2 = Timestamp.valueOf("2023-02-13 12:12:21");

            Booking booking1 = new Booking(0L, u2.getId(), 2L, dateIn, dateOut, 2, 0, time, true, true, false, false);
            BookingDao bookingDao = factory.getBookingDao();
            bookingDao.add(booking1);

            Booking booking2 = new Booking(0L, u2.getId(), app2.getId(), dateIn, dateOut, 2, 0, time2, true, true, true, false);
            bookingDao.add(booking2);

            booking2.setChildrenNumber(5);
            bookingDao.update(booking2);

            Long bookId2 = booking2.getId();
            System.out.println(bookingDao.get(bookId2));
            System.out.println(">>> delete booking2: " +  bookingDao.delete(booking2.getId()));


            System.out.println("\n ------------------\n" + bookingDao.getAll());
            //---------------------

            System.out.println("delete apatment: " + apartmentDao.delete(1L));
            ApartmentPhotosDao photosDao = factory.getApartmentPhotosDao();

            List<String> list1 = photosDao.getAllUrlOfPhotosById(1L);
            System.out.println("-> 1: " + list1);

            List<String> list2 = photosDao.getAllUrlOfPhotosById(2L);
            System.out.println("-> 2:" + list2);

            List<String> list3 = photosDao.getAllUrlOfPhotosById(3L);
            System.out.println("-> 3:" + list3);

            List<String> list4 = photosDao.getAllUrlOfPhotosById(4L);
            System.out.println("-> 4: " + list4);

            System.out.println(" Delete 2: " + photosDao.delete(2L));

*/


      /*  } catch (DaoException e) {
           // throw new RuntimeException(e);
            log.error(e.getMessage());
        }*/
//        response.getWriter().println("SUCCESSFULL!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }



    public void destroy() {
    }
}