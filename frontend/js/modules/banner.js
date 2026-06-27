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
            "http://localhost:8081/cuenta/login",
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

//DATOS PERSONA

const persona = {
  tipoIdentificacion,
  numeroIdentificacion,
  fechaNacimiento,
  nombres,
  apellidos
};

// BUSCAR SI YA EXISTE LA PERSONA
const busquedaResponse = await fetch(
    `http://localhost:8081/personas/documento/${numeroIdentificacion}`
);

let personaData;

if (busquedaResponse.ok) {
    // La persona ya existe, usamos sus datos
    personaData = await busquedaResponse.json();
    console.log("Persona existente encontrada:", personaData);

} else {
    // La persona no existe, la creamos
    const personaResponse = await fetch("http://localhost:8081/personas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(persona)
    });

    if (!personaResponse.ok) {
        const errorData = await personaResponse.json();
        alert(errorData.error);
        return;
    }

    personaData = await personaResponse.json();
    console.log("Persona creada:", personaData);
}

//DATOS CUENTA

const cuenta = {
  email,
  contrasena: password,
  rol: tipoUsuario,
  estado: "ACTIVA",
  persona: {
    id: personaData.id
  }
};

console.log(JSON.stringify(cuenta, null, 2));
console.log("Cuenta:", cuenta);

const cuentaResponse = await fetch("http://localhost:8081/cuenta", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(cuenta)
});

if (!cuentaResponse.ok) {

   const errorData = await cuentaResponse.json();

   alert(errorData.error);

   return;
}

const cuentaData = await cuentaResponse.json();

console.log("Cuenta creada:", cuentaData);

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

forgotPasswordBtn.addEventListener("click", (event) => {
  event.preventDefault();
  loginContainer.classList.remove("active");
  loginContainer.classList.add("recover-active");
});

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
