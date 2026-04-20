
let timeout = null;

const input = document.getElementById("searchInput");
const form = document.getElementById("searchForm");
const status = document.querySelector("select[name='status']");

input.addEventListener("keyup", function () {
    clearTimeout(timeout);
    timeout = setTimeout(() => form.submit(), 400);
});

status.addEventListener("change", function () {
    clearTimeout(timeout);
    timeout = setTimeout(() => form.submit(), 400)
});

//Form Validation
(() => {
    'use strict';
    const forms = document.querySelectorAll('.needs-validation');

    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
})();

const params = new URLSearchParams(window.location.search);
const success = params.get("success");

if (success) {
    let message = "";

    if (success === "added") message = "Job added successfully";
    if (success === "deleted") message = "Job deleted successfully";
    if (success === "updated") message = "Job updated successfully";

    document.getElementById("toastMessage").innerText = message;

    const toast = new bootstrap.Toast(document.getElementById('liveToast'));
    toast.show();
}
//Action Notification timeout
setTimeout(() => {
    const alert = document.getElementById("successAlert");
    if (alert) {
        alert.style.transition = "opacity 0.5s ease";
        alert.style.opacity = "0";

        setTimeout(() => {
            alert.remove();
        }, 500);
    }
}, 3000); // 3 seconds
