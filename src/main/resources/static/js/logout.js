//Grab Logout link from DOM, prevent page reload, send request to API Controller for state handling.
document.getElementById("logoutLink").addEventListener("click", async (e) => {
    e.preventDefault();

    // Fire-and-forget logout: expect 200 from /api/auth/logout, then force a fresh landing page load.
    await fetch("/api/auth/logout", { method: "POST" });
    window.location.href = "/";
});
