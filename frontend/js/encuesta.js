const form = document.querySelector("#studentSurvey");
const progressBar = document.querySelector("#progressBar");
const progressText = document.querySelector("#progressText");
const formMessage = document.querySelector("#formMessage");
const futureIdeaField = document.querySelector("#futureIdeaField");
const futureIdeaInput = document.querySelector("[name='ideaFuturo']");
let isSubmitting = false;

const getGroupedControlNames = () => {
  const controls = [...form.querySelectorAll("input, textarea")];
  return [...new Set(controls.map((control) => control.name).filter(Boolean))];
};

const getFieldValue = (name) => {
  const field = form.elements[name];
  const controls = field instanceof RadioNodeList ? [...field] : [field];

  if (controls[0]?.type === "radio") {
    return controls.some((control) => control.checked);
  }

  if (controls[0]?.type === "checkbox") {
    return controls.some((control) => control.checked);
  }

  return controls[0]?.value.trim().length > 0;
};

const updateProgress = () => {
  const fieldNames = getGroupedControlNames().filter((name) => name !== "ideaFuturo");
  const completedFields = fieldNames.filter(getFieldValue).length;
  const percentage = Math.round((completedFields / fieldNames.length) * 100);

  progressBar.style.width = `${percentage}%`;
  progressText.textContent = `${percentage}% completado`;
};

const updateFutureIdeaState = () => {
  const selectedValue = form.elements.tieneIdeaFuturo.value;
  const isRequired = selectedValue === "Si";

  futureIdeaInput.required = isRequired;
  futureIdeaField.classList.toggle("is-required", isRequired);

  if (!isRequired) {
    futureIdeaInput.value = "";
    futureIdeaInput.classList.remove("field-error");
  }
};

const enforceCheckboxLimits = () => {
  document.querySelectorAll("[data-limit-group]").forEach((group) => {
    const limit = Number(group.dataset.limit);
    const checkboxes = [...group.querySelectorAll("input[type='checkbox']")];

    checkboxes.forEach((checkbox) => {
      checkbox.addEventListener("change", () => {
        const selected = checkboxes.filter((item) => item.checked);

        if (selected.length > limit) {
          checkbox.checked = false;
          formMessage.textContent = `Solo puedes elegir ${limit} opciones en esta pregunta.`;
        } else {
          formMessage.textContent = "";
        }

        updateProgress();
      });
    });
  });
};

const collectSurveyData = () => {
  const data = new FormData(form);
  const values = Object.fromEntries(data.entries());

  values.intereses = data.getAll("intereses");
  values.aprendizaje = data.getAll("aprendizaje");
  values.fechaEnvio = new Date().toISOString();

  return values;
};

form.addEventListener("input", (event) => {
  event.target.classList.remove("field-error");
  updateFutureIdeaState();
  updateProgress();
});

form.addEventListener("change", () => {
  updateFutureIdeaState();
  updateProgress();
});

form.addEventListener("reset", () => {
  window.setTimeout(() => {
    if (!isSubmitting) {
      formMessage.textContent = "";
    }

    isSubmitting = false;
    updateFutureIdeaState();
    updateProgress();
  }, 0);
});

form.addEventListener("submit", (event) => {
  event.preventDefault();

  if (!form.checkValidity()) {
    const invalidField = form.querySelector(":invalid");
    invalidField?.classList.add("field-error");
    invalidField?.focus();
    formMessage.textContent = "Revisa los campos pendientes antes de enviar.";
    return;
  }

  const surveyData = collectSurveyData();
  localStorage.setItem("growupEncuestaInicial", JSON.stringify(surveyData));

  isSubmitting = true;
  formMessage.textContent = "Encuesta guardada correctamente en este navegador.";
  form.reset();
});

enforceCheckboxLimits();
updateFutureIdeaState();
updateProgress();
