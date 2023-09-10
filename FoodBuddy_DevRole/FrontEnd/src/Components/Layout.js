import React from 'react';
import './Layout.css';



const Layout = ({ children }) => {
  return (
    <div>
      <footer style={{ backgroundColor: '#e6e6e6', margin: 0, padding: '1rem' }}>
        <p>FoodBuddy App</p>
      </footer>
    </div>
  );
};


export default Layout;
