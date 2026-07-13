export function initBanner() {
  console.log("Banner iniciado");
}

//VALIDACIÓN DE USUARIOS EN EL INICIO SE SESIÓN

const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async (e) => {

    e.preventDefault();

    try {

        const email =
            document.getElementById("loginEmail").value.trim();

        const contrasena =
            document.getElementById("loginPassword").value.trim();

        const loginData = {
            email,
            contrasena
        };

        const response = await fetch(
            "https://growup-production-bd7f.up.railway.app/cuenta/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(loginData)
            }
        );

        if (!response.ok) {

            alert("Credenciales incorrectas");

            return;
        }

        const cuenta = await response.json();

console.log("Login correcto:", cuenta);

// Redirigir según el rol del usuario autenticado

switch (cuenta.rol) {

    case "ESTUDIANTE":
    window.location.href = "./pages/panel_estudiante.html";
    break;

case "MENTOR":
    window.location.href = "./pages/panel_mentor.html";
    break;

case "COORDINADOR":
    window.location.href = "./pages/panel_coordinador.html";
    break;

    default:
        alert("Rol no reconocido");
}

    } catch (error) {

        console.error(error);

        alert("Error al iniciar sesión");
    }

});


// REGISTRO USUARIOS

const registerForm = document.getElementById("registerForm")
registerForm.addEventListener("submit", async (e) => {

   e.preventDefault();

   try {

const tipoUsuario =
  document.getElementById("tipoUsuarioRegister").value;

const tipoIdentificacion =
  document.getElementById("tipoIdentificacionRegister").value;

const numeroIdentificacion =
   document.getElementById("numeroIdentificacion").value;

const nombres =
   document.getElementById("nombres").value;

const apellidos =
   document.getElementById("apellidos").value;

const fechaNacimiento =
   document.getElementById("fechaNacimiento").value;

const email =
  document.getElementById("registerEmail").value;

const password =
  document.getElementById("registerPassword").value;

console.log("Email:", email);
console.log("Password:", password);
console.log("Tipo usuario:", tipoUsuario);

//DATOS CUENTA

const registroData = {
    tipoIdentificacion,
    numeroIdentificacion,
    fechaNacimiento,
    nombres,
    apellidos,
    email,
    contrasena: password,
    rol: tipoUsuario
};

const registroResponse = await fetch("https://growup-production-bd7f.up.railway.app/registro", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(registroData)
});

if (!registroResponse.ok) {
    const errorData = await registroResponse.json();
    alert(errorData.error);
    return;
}

const cuentaData = await registroResponse.json();
console.log("Registro completado:", cuentaData);

alert("Usuario registrado correctamente");
registerForm.reset();
loginContainer.classList.remove("active");
   } catch(error) {
      console.error(error);
      alert("Error al registrar usuario");
   }
});

// BANNER - CARGAR MÁS INFORMACIÓN DE LOS PROGRAMAS DE FORMACIÓN
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
const loginOverlay = document.getElementById("login-overlay");

const loginContainer = document.querySelector(".login-container");
const registerBtn = document.getElementById("btn-register");
const loginBtn = document.getElementById("btn-login");
const forgotPasswordBtn = document.getElementById("btn-forgot-password");
const backLoginBtn = document.getElementById("btn-back-login");
const recoverForm = document.getElementById("recover-form");

const closeLoginPanel = () => {
  loginPanel.classList.remove("active");
  loginOverlay.classList.remove("active");
  loginPanel.setAttribute("aria-hidden", "true");
  loginContainer.classList.remove("active");
  loginContainer.classList.remove("recover-active");
};

// Abrir panel
openLogin.addEventListener("click", () => {
  loginPanel.classList.add("active");
  loginOverlay.classList.add("active");
  loginPanel.setAttribute("aria-hidden", "false");
});

// Cerrar al hacer click fuera del panel
loginOverlay.addEventListener("click", () => {
  closeLoginPanel();
});

// Ir a registro
registerBtn.addEventListener("click", () => {
  loginContainer.classList.remove("recover-active");
  loginContainer.classList.add("active");
});

// Volver a login
loginBtn.addEventListener("click", () => {
  loginContainer.classList.remove("active");
  loginContainer.classList.remove("recover-active");
});

if (forgotPasswordBtn) {
    forgotPasswordBtn.addEventListener("click", (event) => {
        event.preventDefault();
        loginContainer.classList.remove("active");
        loginContainer.classList.add("recover-active");
    });
}

backLoginBtn.addEventListener("click", (event) => {
  event.preventDefault();
  loginContainer.classList.remove("recover-active");
});

recoverForm.addEventListener("submit", (event) => {
  event.preventDefault();
  const recoveryEmail = recoverForm.querySelector(
    'input[name="recoveryEmail"]',
  );
  alert(
    `Si el correo ${recoveryEmail.value.trim()} está registrado, recibirás un enlace de recuperación.`,
  );
  recoverForm.reset();
  loginContainer.classList.remove("recover-active");
});
