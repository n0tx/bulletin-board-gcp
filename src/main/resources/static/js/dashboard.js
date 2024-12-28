// Sembunyikan elemen setelah 5 detik
document.addEventListener("DOMContentLoaded", function() {
    // input search hanya muncul pada list
    const currentPath = window.location.pathname;
    const searchForm = document.getElementById("search");

    if (currentPath === "/") {
        searchForm.classList.remove("d-none");
    } else {
        searchForm.classList.add("d-none");
    }
});

document.getElementById('confirmDeleteBtn').addEventListener('click', function () {
    document.getElementById('deleteForm').submit();
});