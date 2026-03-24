// USUARIOS FICTICIOS POR ROL

const demoUsers = [
  {
    role: "st",
    document: 1001,
    password: "estudiante1001",
    name: "Alex Estudiante",
    redirect: "panel_estudiante.html",
  },
  {
    role: "men",
    document: 2001,
    password: "mentor2001",
    name: "Belen Mentor",
    redirect: "panel_mentor.html",
  },
  {
    role: "coor",
    document: 3001,
    password: "coordinado3001",
    name: "Carla Coordinador",
    redirect: "panel_coordinador.html",
  },
];

//VALIDACIÓN DE USUARIOS

const loginForm = document.querySelector(".login-form-box.login form");

loginForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const userType = loginForm.querySelector('select[name="tipoUsuario"]').value;

  const documentValue = loginForm
    .querySelector('input[type="text"]')
    .value.trim();
  const passwordValue = loginForm
    .querySelector('input[type="password"]')
    .value.trim();

  if (!userType || !documentValue || !passwordValue) {
    alert("Completa todos los campos");
    return;
  }

  const userFound = demoUsers.find(
    (user) =>
      user.role === userType &&
      user.document === documentValue &&
      user.password === passwordValue
  );

  if (!userFound) {
    alert("Credenciales incorrectas");
    return;
  }

  alert(`Bienvenido ${userFound.name}`);
  window.location.href = userFound.redirect;
});

// BANNER - CARGAR MÁS
const loadMoreBtn = document.querySelector("#load-more");
let currentItem = 4;

if (loadMoreBtn) {
  loadMoreBtn.onclick = () => {
    const boxes = [...document.querySelectorAll(".box-container .box")];

    for (let i = currentItem; i < currentItem + 4 && i < boxes.length; i++) {
      boxes[i].style.display = "inline-block";
    }

    currentItem += 4;

    if (currentItem >= boxes.length) {
      loadMoreBtn.style.display = "none";
    }
  };
}

// LOGIN PANEL
const openLogin = document.getElementById("btn-login-register");
const loginPanel = document.getElementById("login-panel");
const closeLogin = document.getElementById("close-login");

const loginContainer = document.querySelector(".login-container");
const registerBtn = document.getElementById("btn-register");
const loginBtn = document.getElementById("btn-login");

// Abrir panel
openLogin.addEventListener("click", () => {
  loginPanel.classList.add("active");
});

// Cerrar panel
closeLogin.addEventListener("click", () => {
  loginPanel.classList.remove("active");
  loginContainer.classList.remove("active");
});

// Ir a registro
registerBtn.addEventListener("click", () => {
  loginContainer.classList.add("active");
});

// Volver a login
loginBtn.addEventListener("click", () => {
  loginContainer.classList.remove("active");
});

document.querySelector(".login-btn").addEventListener("click", function(){
this.textContent = "Cargando...";
});