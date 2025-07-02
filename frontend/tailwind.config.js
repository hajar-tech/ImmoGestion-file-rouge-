// tailwind.config.js
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}", // Important for Tailwind to scan your Angular files
  ],
  theme: {
    extend: {
      colors: {
        'marine-blue': '#2C3E50',
        'light-blue': '#3498DB',
        'soft-green': '#2ECC71',
        'pearl-grey': '#ECF0F1',
        'anthracite-grey': '#34495E',
        'soft-red': '#E74C3C',
      },
    },
  },
  plugins: [],
}

