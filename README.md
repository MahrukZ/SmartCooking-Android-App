# SmartCooking



## Table of Contents

- [About](#about)

- [Key Features](#key-features)

- [Technology Stack](#technology-stack)

- [Getting Started](#getting-started)

- [Permissions](#permissions)


## About

SmartCooking is an Android app designed to enhance your cooking experience by providing a variety of useful features. Often, we all have experienced the ambiguity of not knowing what to cook. This is a common problem attributed to everyone, from students, and homemakers to the working class. Estimates say that approximately 35 minutes are wasted in such a dilemma (Stroud, 2017). With SmartCooking, you can easily explore new recipes, manage your ingredients, and organize your cooking preferences all in one place. Whether you're a seasoned chef or a beginner in the kitchen, this app aims to make your culinary journey more delightful and convenient.
Furthermore, it also conserves the environment by recommending minimal-to-zero waste recipes even with leftovers that generally end up in the trash. Therefore, these multifold benefits make SmartCooking an ideal app for busy people who want delicious meals while caring for the environment



## Key Features

- **Ingredient Input**:
    SmartCooking allows you to input your available ingredients in a seamless manner. You have two options for inputting ingredients:
      Manual Input: Type in the ingredients you have in your kitchen.
      Voice Interaction: Use voice commands to add ingredients quickly and effortlessly.
  
- **Recipe Search and Viewing**:
    Once you've entered your ingredients, the app provides a powerful recipe search functionality. SmartCooking will find recipes that match the ingredients you have  available, making it easier to cook delicious dishes without having to visit multiple recipe websites.
    For each recipe, you can view detailed instructions, ingredient lists, cooking times, and even user reviews to help you make informed choices.

- **Favorite Recipes**:
    If you discover a recipe that you love and want to revisit later, simply mark it as a favorite. These favorite recipes are stored in a dedicated favorites fragment for easy access, allowing you to quickly find and cook your preferred dishes again.

- **Shopping List**:
    SmartCooking helps you manage your shopping list effortlessly. When you find a recipe you want to cook, you can add the required ingredients directly to your shopping list with just a tap on the plus icon next to each ingredient. This feature ensures that you never miss any essential ingredients while grocery shopping.

- **Adding Ingredients from Shopping List**:
    In addition to adding ingredients from recipes, you can manually add items to your shopping list from a dedicated shopping list section. This flexibility enables you to keep track of all your kitchen essentials conveniently.


## Technology Stack

In the development of the SmartCooking Android app, careful consideration was given to selecting the appropriate technologies to ensure the app's functionality, user experience, and maintainability. The following sections outline the key technology choices made during the development process:

**Java Programming Language**

Choice Rationale: Java is the official programming language for Android app development and has a strong presence in the Android ecosystem. It is a mature and widely-used language, providing a robust foundation for building feature-rich and reliable applications.

**Advantages**:

- Robust Ecosystem: Java benefits from a vast array of libraries, frameworks, and resources tailored for Android development, simplifying the development process and accelerating feature implementation.

- Platform Independence: Java's "write once, run anywhere" principle allows SmartCooking to target a wide range of Android devices with minimal adjustments, ensuring compatibility across various Android versions.

- Object-Oriented Programming: Java's object-oriented nature promotes modular and well-organized code, enabling easier maintenance and scalability as the app evolves.


**Inbuilt Speech Recognizer for Voice Interaction**

SmartCooking allows users to input ingredients through voice interaction. The inbuilt Speech Recognizer provided by Android was selected over paid alternatives like the Google API due to its cost-effectiveness and support for intermittent speech recognition.

**Advantages**:

- Cost-Effectiveness: The inbuilt Speech Recognizer is available for free, making it a budget-friendly option for the voice input functionality.

- Intermittent Speech Recognition: The inbuilt Speech Recognizer supports intermittent speech input, allowing users to provide multiple ingredients in one go with small pauses, aligning perfectly with the app's requirements.

- Battery and Bandwidth Optimization: As the inbuilt Speech Recognizer is optimized for intermittent speech recognition, it consumes less battery and bandwidth compared to continuous speech recognition, contributing to a better user experience.

**Room Database**

To enable users to store data in a database, Room was chosen over using direct SQLite queries due to its enhanced capabilities and ease of use.

**Advantages**:

- Compile-Time Validation: Room validates database queries at compile time, reducing the likelihood of runtime exceptions and potential crash issues, leading to a more stable and reliable app.

- Simplified Data Mapping: Room automatically maps database objects to Java POJOs, eliminating the need for manual boilerplate code. This streamlines the codebase, leading to a cleaner and more maintainable architecture.

- Schema Changes Handling: Room simplifies the handling of database schema changes, automatically updating the schema as the app evolves, reducing the developer's burden of managing SQL queries.

**Volley for API Requests**

Volley was selected as the preferred networking library for making API requests due to its inbuilt nature, image loading support, and ease of integration.

**Advantages**:

- Inbuilt Android Library: Volley is an inbuilt library provided by Android, making it a secure and supported choice. Regular updates by Google developers ensure its reliability and compatibility with the latest Android versions.

- Image Loading Support: Volley's built-in support for image loading through image requests eliminates the need for an additional library, making it efficient for handling images in the app.

- Simplicity and Integration: As an inbuilt library, Volley integrates seamlessly with the Android ecosystem, making it easier to use for making API requests.

By leveraging these technology choices, SmartCooking offers a delightful and user-friendly experience to cooking enthusiasts, empowering them to explore new recipes, manage ingredients, and cook with ease. The selected technologies contribute to the app's performance, reliability, and ease of maintenance, ensuring a smooth cooking journey for users.


## Getting started


To start using SmartCooking, follow these steps:

- Clone this repository to your local machine using the following command:

```

git clone https://github.com/Mahrukhz/SmartCooking-Android.git

```

- Open the project in Android Studio.

- Build and run the app on your Android device or emulator.
- Ensure that you have the Android SDK and platform tools installed for API 30 (Pixel 4).
- Build and run the app on the Pixel 4 emulator or a physical Pixel 4 device.


## Permissions

SmartCooking requires the following permissions:

- Internet: To search for recipes and fetch data from the server.
- Microphone (optional): For voice interaction when adding ingredients.
