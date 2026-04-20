document.addEventListener("DOMContentLoaded", function () {          // Dark mode Scripting

    const toggleBtn = document.getElementById("themeToggle");

    if (!toggleBtn) return; // prevents error on pages without button

    // Load saved theme
    if (localStorage.getItem("theme") === "dark") {
        document.body.classList.add("dark-mode");
        toggleBtn.innerText = "☀️";
    }

    toggleBtn.addEventListener("click", () => {
        document.body.classList.toggle("dark-mode");

        if (document.body.classList.contains("dark-mode")) {
            localStorage.setItem("theme", "dark");
            toggleBtn.innerText = "☀️";
        } else {
            localStorage.setItem("theme", "light");
            toggleBtn.innerText = "🌙";
        }
    });

});