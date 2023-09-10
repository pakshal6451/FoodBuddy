import { Register } from "./Register";
import { Route, Routes } from 'react-router-dom';
import { Login } from "./Login";
import {HomePage} from "./Home";


export function Routing() {
    return (
        <>
            <Routes>
                <Route path='/' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/home' element={<HomePage />} />
            </Routes>
        </>
    );


}


