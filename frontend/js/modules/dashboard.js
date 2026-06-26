export function initDashboard() {
  const logoutBtn = document.querySelector(".logout-btn");
  const cards = document.querySelectorAll(".card");
  const container = document.querySelector(".container");
  const registerBtn = document.querySelector(".register-btn");
  const loginBtn = document.querySelector(".login-btn");

  if (logoutBtn) {
    logoutBtn.addEventListener("click", () => {
      window.location.href = "../index.html";
    });
  }

  cards.forEach((card) => {
    card.addEventListener("click", () => {
      cards.forEach((item) => item.classList.remove("active"));
      card.classList.add("active");
    });
  });

  if (container && registerBtn && loginBtn) {
    registerBtn.addEventListener("click", () => {
      container.classList.add("active");
    });

    loginBtn.addEventListener("click", () => {
      container.classList.remove("active");
    });
  }
}
