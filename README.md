# RecetasApp  

Aplicación móvil de Android desarrollada en **Kotlin** con **Jetpack Compose**, que permite a los usuarios gestionar recetas de cocina de manera sencilla y eficiente.  
La app incluye autenticación, almacenamiento local, filtros avanzados y un diseño moderno basado en **Material Design 3**.  

---

## ✨ Características principales  

### 🔐 Autenticación  
- Pantalla de inicio de sesión con credenciales predefinidas.  
- Mantenimiento de sesión activa incluso si se cierra la app.  
- Opción para cerrar sesión de forma segura.  

### 🍲 Gestión de recetas  
- Agregar nuevas recetas con: título, descripción, tiempo de preparación, imagen y opción de favorito.  
- Listado de recetas guardadas mostrando imagen, título, tiempo y estado de favorito.  
- Visualización del detalle completo de cada receta.  
- Posibilidad de marcar o desmarcar recetas como favoritas.  

### 🎯 Filtros y ordenamiento  
- Filtrar por recetas favoritas.  
- Ordenar por tiempo de preparación (menor a mayor).  

### 📌 Extras implementados  
- Agregar fotos desde la galería.  
- Visualización de imágenes en lista y detalle de recetas.  
- Tema claro y oscuro.  
- Pantalla de carga (Splash Screen).  
- Uso de fuente personalizada.  

---

## 🛠️ Tecnologías y librerías utilizadas  

### 📐 Arquitectura  
- **MVVM** (Model-View-ViewModel) para separación de responsabilidades.  
- **Flow** para una UI reactiva y eficiente.  
- **Room Database** para almacenamiento local persistente.  

### 📚 Librerías  
- **Dagger Hilt** (2.51.1) – Inyección de dependencias.  
- **Hilt Navigation Compose** (1.2.0) – Integración de Hilt con Navigation Compose.  
- **Room** (2.6.1) – Base de datos local.  
- **Navigation Compose** (2.8.6) – Manejo de navegación.  
- **SplashScreen API** (1.0.1) – Implementación de pantalla de carga.  
- **Kotlin Serialization** (1.7.3) – Serialización de datos.  
- **Lottie** (4.2.0) – Animaciones personalizadas.  
- **Coil** (2.6.0) – Carga eficiente de imágenes.  

---

## 📸 Capturas de pantalla  

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/74f63fbd-710d-4357-8aad-4bd40cfa6232" width="250"/></td>
    <td><img src="https://github.com/user-attachments/assets/26fbaea5-5c3d-4b1c-9455-23b6fda79259" width="250"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/dc118c04-c70e-41a7-960c-85f258f30588" width="250"/></td>
    <td><img src="https://github.com/user-attachments/assets/8336354c-a1b6-471f-b791-c715fcbe99d3" width="250"/></td>
  </tr>
</table>  

---

## 🚀 Ejecución  

1. Clonar este repositorio:  
   ```bash
   git clone https://github.com/Yosel17/RecipeApp.git
