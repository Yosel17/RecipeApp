# 🍽️ RecetasApp

RecetasApp es una 📱 aplicación de Android nativa desarrollada con Kotlin y Jetpack Compose que permite a los usuarios gestionar recetas de cocina de manera sencilla y eficiente. La app cuenta con autenticación, almacenamiento local, filtros y un diseño moderno basado en Material 3. 🎨

## ✨ Características Principales

### 🔐 Autenticación
- 🔑 Pantalla de inicio de sesión.
- ✅ Acceso restringido a credenciales predefinidas.
- 🔄 Mantener la sesión activa aunque se cierre la app.
- 🚪 Opción para cerrar sesión.

### 🍲 Gestión de Recetas
- ➕ Agregar nuevas recetas con:
  - 🏷️ Título.
  - 📝 Descripción.
  - ⏳ Tiempo de preparación (en minutos).
  - ⭐ Opción para marcar como favorita.
  - 🖼️ Imagen.
- 📋 Listado de recetas guardadas con:
  - 🖼️ Imagen.
  - 🏷️ Título.
  - ⏳ Tiempo de preparación.
  - ⭐ Estado de favorito.
- 🔍 Visualización del detalle de una receta.
- ⭐ Marcado de recetas como favoritas.

### 🎯 Filtros y Ordenamiento
- 🔍 Filtrar recetas por:
  - ⭐ Solo favoritas.
  - ⏳ Tiempo de preparación (de menor a mayor).

### 🎁 Extras Implementados
- 📸 Agregar una foto desde la galería a cada receta.
- 🖼️ Mostrar imágenes en el listado y en el detalle de la receta.
- 🌗 Tema claro y oscuro.
- 🚀 Pantalla de carga (Splash Screen).
- ✍️ Fuente personalizada.

## 🛠️ Tecnologías y Librerías Utilizadas

### 📐 Arquitectura
- 🏛️ **MVVM** (Model-View-ViewModel) para una mejor separación de responsabilidades.
- 🔄 **Flow** para una UI reactiva y eficiente.
- 🗄️ **Room Database** para almacenamiento local en tiempo real.

### 📚 Librerías
- 🏗 **Dagger Hilt** (2.51.1) - Inyección de dependencias.
- 🗺 **Hilt Navigation Compose** (1.2.0) - Integración de Hilt con Navigation Compose.
- 🗄 **Room** (2.6.1) - Base de datos local.
- 🚏 **Navigation Compose** (2.8.6) - Manejo de navegación en la app.
- 🖼 **SplashScreen API** (1.0.1) - Implementación de pantalla de carga.
- 🔄 **Kotlin Serialization** (1.7.3) - Serialización de datos.
- 🎭 **Lottie** (4.2.0) - Animaciones personalizadas.
- 🖼 **Coil** (2.6.0) - Carga eficiente de imágenes.

