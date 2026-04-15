Hi this Noir,

I just finished deployment at 6am, so i add the description later but i am providing the live link below:
https://job-tracker-pq3x.onrender.com

For anyone Curiuos, i used render to deploy the project And i will make UI Changes later within 7-10 Days will update the description along with it.

# JobTracker (V2)

This is a job application tracking web app that I built while learning Spring Boot. The idea was simple — I wanted something where I could track all the jobs I apply to instead of keeping everything scattered.

Over time, it turned into a much bigger project than I initially expected, especially after adding authentication, user-specific data, and search features.

---

## 🚀 What it does

* Users can register and log in
* Each user can add, edit, and delete their own job applications
* Jobs are stored per user (so no data overlap)
* Admin role can view all users and jobs
* Search and filter jobs by company, role, and status
* Live search (auto updates after typing delay)
* Clean UI using Bootstrap

---

## 🧠 Tech Stack

**Backend**

* Java
* Spring Boot
* Spring Data JPA
* Hibernate

**Frontend**

* Thymeleaf
* HTML/CSS
* Bootstrap

**Database**

* H2 (for now)

**Security**

* BCrypt password hashing
* Session-based authentication

---

## 📌 Features I focused on

* **Authentication & Authorization**
  Implemented login/register with password hashing and session handling.

* **User-specific data isolation**
  Each user only sees their own jobs.

* **Admin panel**
  Basic role-based access to view all users and jobs.

* **Search + Filter system**
  Implemented using a custom JPQL query to handle multiple conditions (keyword + status).

* **Live search (debounced)**
  Added a delay-based search so results update automatically without clicking a button.

---

## ⚠️ Things I learned (and struggled with)

* Difference between REST controllers and MVC controllers
* How Spring Data JPA query methods work (and where they break)
* Why complex filtering is better handled with custom queries
* Session handling vs Spring Security defaults
* Deployment issues (Docker + environment setup was confusing at first)

---

## 🔧 Future Improvements

* Switch from H2 to MySQL
* Add sorting and pagination
* Improve UI further
* Add Google Sign-In
* Possibly convert frontend to React later

---

## 📝 Notes

This is my first proper full-stack project using Spring Boot, so the code might not be perfect, but I focused on making it functional and improving it step by step.

---

## 📷 Preview

(Add screenshots here)

---

## 💡 Why I made this

Mostly to learn how real applications handle:

* multiple users
* authentication
* database relationships
* and dynamic UI features

---

If you have any suggestions or feedback, feel free to share.

PS: And yes i took some help of AI over the development of this project, but its not built using AI. This readme is mostly AI generated though :) 
