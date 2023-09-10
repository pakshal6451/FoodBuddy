import "../App.css";
import React, { useState, useEffect } from "react";
import {GroupManagement} from "./GroupManagement";
import {InventoryManagement} from "./InventoryManagement";
import RecipeManagement from "./RecipeManagement";
import ExpenseManagement from "./ExpenseManagement";
import AdbIcon from '@mui/icons-material/Adb';
import Typography from '@mui/material/Typography';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from "react-router-dom";
import { loginAction } from '../redux/actions/LoginLogoutAction'


export const HomePage = () => {
const [activeTab, setActiveTab] = useState("");
const navigate = useNavigate();
const dispatch = useDispatch();
const userName = useSelector((state) => state.loginLogoutReducer.emailId);

const renderTab = () => {
    switch (activeTab) {
      case "GroupManagement":
        return (userName==="")?navigate('/'):<GroupManagement />;
      case "InventoryManagement":
        return (userName==="")?navigate('/'):<InventoryManagement />;
      case "RecipeManagement":
        return (userName==="")?navigate('/'):<RecipeManagement />;
      case "ExpenseManagement":
        return (userName==="")?navigate('/'):<ExpenseManagement />;
      case "Home" : return handleUserLogout()
    }
  };

const handleUserLogout = async() => {
    console.log("In handle User Logout")
    dispatch(loginAction(""))
    navigate('/')
  }

  useEffect(() => {
    setActiveTab("GroupManagement")
  },[]);
return (
    <div className="Home">
      <header style={{backgroundColor:"#3366cc"}}>
        <nav>
          <ul >
            <li>

          <Typography
            variant="h7"
            noWrap
            component="label"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
           <AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />  Food Buddy
          </Typography>
            </li>
            <li style={{margin:"1%"}}>
              <button  onClick={() => setActiveTab("GroupManagement")}>
                Group Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("InventoryManagement")}>
                Inventory Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("RecipeManagement")}>
                Recipe Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("ExpenseManagement")}>
                Expense Management
              </button>
            </li>
            <li>
              <button onClick={() => setActiveTab("Home")}>Logout</button>
            </li>
          </ul>
        </nav>
      </header>
      <main>{renderTab()}</main>
    </div>
  );
}
export default HomePage;
