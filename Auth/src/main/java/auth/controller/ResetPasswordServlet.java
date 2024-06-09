package auth.controller;

import auth.dao.UserDAO;
import auth.model.User;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer le nouveau mot de passe saisi par l'utilisateur
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Vérifier que les deux mots de passe correspondent
        if (!newPassword.equals(confirmPassword)) {
            // Afficher un message d'erreur si les mots de passe ne correspondent pas
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }

        // Vérifier que le mot de passe a au moins 8 caractères
        if (newPassword.length() < 8) {
            // Afficher un message d'erreur si le mot de passe est trop court
            request.setAttribute("errorMessage", "Password must be at least 8 characters long.");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }

        // Récupérer l'e-mail de l'utilisateur à partir de la session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Mettre à jour le mot de passe dans la base de données
        UserDAO userDAO = new UserDAO();
        try {
            // Vérifier si l'utilisateur existe dans la base de données
            User user = userDAO.selectUserByEmail(email);
            if (user != null) {
                // Mettre à jour le mot de passe de l'utilisateur
                user.setPassword(newPassword);
                boolean passwordUpdated = userDAO.updateUser(user);
                if (passwordUpdated) {
                    // Afficher un message de succès si le mot de passe a été mis à jour avec succès
                    request.setAttribute("successMessage", "Password updated successfully.");
                    // Rediriger vers la page de login
                    response.sendRedirect("login.jsp");
                    return;
                } else {
                    // Afficher un message d'erreur si la mise à jour du mot de passe a échoué
                    request.setAttribute("errorMessage", "Failed to update password.");
                }
            } else {
                // Afficher un message d'erreur si l'utilisateur n'existe pas dans la base de données
                request.setAttribute("errorMessage", "User does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En cas d'erreur SQL, afficher un message d'erreur
            request.setAttribute("errorMessage", "Failed to update password: " + e.getMessage());
        }

        // Dispatcher les attributs de la requête vers la page JSP
        request.getRequestDispatcher("reset-password.jsp").forward(request, response);
    }
}
