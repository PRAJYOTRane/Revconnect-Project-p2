document.addEventListener("DOMContentLoaded", async () => {
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "/login.html";
        return;
    }
    try {
        const response = await fetch("/api/user/me", {
            method: "GET",
            headers: { "Authorization": `Bearer ${token}` }
        });
        if (response.status === 401 || response.status === 403) {
            localStorage.removeItem("token");
            window.location.href = "/login.html";
            return;
        }
        if (!response.ok) {
            throw new Error("Failed to load profile");
        }
        const user = await response.json();
        console.log("Logged-in user:", user);
        document.getElementById("profileUsername").textContent = user.username;
        document.getElementById("profileAccountType").textContent = user.accountType;
    } catch (error) {
        console.error("Error loading profile:", error);
    }
    const logoutBtn = document.getElementById("logoutBtn");
    if (logoutBtn) {
        logoutBtn.addEventListener("click", () => {
            localStorage.removeItem("token");
            window.location.href = "/login.html";
        });
    }
});