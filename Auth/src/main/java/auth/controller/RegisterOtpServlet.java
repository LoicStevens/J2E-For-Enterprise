package auth.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.User;

@WebServlet("/registerOtp")
public class RegisterOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les données du formulaire
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Liste des messages d'erreur
        List<String> errorMessages = new ArrayList<>();

        // Validation des champs
        if (username == null || username.isEmpty()) {
            errorMessages.add("User name is require.");
        }
        if (email == null || email.isEmpty()) {
            errorMessages.add("email address is require.");
        }
        if (password == null || password.isEmpty()) {
            errorMessages.add("Password is require.");
        } else if (password.length() < 8) {
            errorMessages.add("The password should be at least 8 characters.");
        }
        if (role == null || role.isEmpty()) {
            errorMessages.add("Le rôle est requis.");
        }

        // Si des erreurs sont présentes, rediriger vers register.jsp
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Créer un nouvel utilisateur et le stocker en session
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Générer un OTP aléatoire
        String otp = generateOtp();

        // Envoyer l'OTP par e-mail
        try {
            sendOtpByEmail(email, otp);
            session.setAttribute("otp", otp);
            response.sendRedirect("enter-otp2.jsp");
        } catch (MessagingException e) {
            e.printStackTrace();
            request.setAttribute("message", "Échec de l'envoi de l'OTP. Veuillez réessayer.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }
    }

    private String generateOtp() {
        Random rand = new Random();
        int otpValue = rand.nextInt(900000) + 100000; // Générer un OTP de 6 chiffres
        return String.valueOf(otpValue);
    }

    private void sendOtpByEmail(String to, String otp) throws MessagingException {
        // Configurer les propriétés pour l'envoi de l'e-mail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Authentification
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("destructeurkratos@gmail.com", "lycptxurzomszzob");
            }
        });

        // Composer le message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("destructeurkratos@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Team JOBOS : Welcome dear !");
        message.setText("Your OTP is: " + otp);

        // Envoyer le message
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported.");
    }
}
