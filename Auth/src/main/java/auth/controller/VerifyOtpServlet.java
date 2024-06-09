package auth.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VerifyOtpServlet")
public class VerifyOtpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'OTP saisi par l'utilisateur depuis le formulaire
        String enteredOtp = request.getParameter("otp");

        // Récupérer l'OTP stocké en session
        HttpSession session = request.getSession();
        int storedOtp = (int) session.getAttribute("otp");

        // Comparer les OTP
        if (enteredOtp != null && enteredOtp.equals(String.valueOf(storedOtp))) {
            // Si les OTP correspondent, rediriger vers une page de réinitialisation de mot de passe
            response.sendRedirect("reset-password.jsp");
        } else {
            // Si les OTP ne correspondent pas, afficher un message d'erreur
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("EnterOtp.jsp").forward(request, response);
        }
    }
}


