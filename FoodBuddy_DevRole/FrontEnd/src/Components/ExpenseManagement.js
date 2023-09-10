import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import React, { useState, useEffect, useLayoutEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { styled } from '@mui/material/styles';


function GroupExpensesTable(props) {
const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));

  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));


  const { groupExpenses } = props;

  return (
    <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
      <TableHead>
        <TableRow>
          <StyledTableCell align="center">User Email</StyledTableCell>
          <StyledTableCell align="center">Total Amount</StyledTableCell>
        </TableRow>
      </TableHead>

      <TableBody>
        {groupExpenses.map((expense,index) => (
          <StyledTableRow
            key={index}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
            <TableCell align="center">{expense.emailId}</TableCell>
            <TableCell align="center">{expense.amount}</TableCell>
          </StyledTableRow>
        ))}
      </TableBody>
    </Table>
  );
}

function GroupExpenses(props) {
  const { groupExpenses } = props;

  return (
    <div>
      <h2>Group Expenses</h2>
      <GroupExpensesTable groupExpenses={groupExpenses} />
    </div>
  );
}

function Expenses() {
  const [showTotal, setShowTotal] = useState(false);
  const [showGroup, setShowGroup] = useState(false);
  const emailId = useSelector((state) => state.loginLogoutReducer.emailId);
  const userGroupNumber = useSelector(
    (state) => state.groupManagementReducer.groupCode
  );
  const [groupExpenses, setGroupExpenses] = useState([]);

  const handleTotalClick = (event) => {
    setShowTotal(true);
    setShowGroup(false);
    getTotalExpense(emailId);
  };

  const getTotalExpense = (formData) => {
    axios
      .post("http://172.17.0.203:8080/api/v1/expenses/total", { emailId }, {
        headers: {
          "Content-Type": "application/json"
        }
      })
      .then((response) => {
        if (response.data.status === "success") {
          console.log("Item added");
          console.log(response);
          setShowTotal(response.data.totalExpenses);
        } else {
          alert("Expense not found");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleGroupClick = () => {
    setShowTotal(false);
    setShowGroup(true);
    getGroupExpense(userGroupNumber);
  };

  const getGroupExpense = (userGroupNumber) => {
    axios
      .post("http://172.17.0.203:8080/api/v1/expenses/groupExpenses", { userGroupNumber }, {
        headers: {
          "Content-Type": "application/json"
        }
      })
      .then((response) => {
        if (response.data.status === "success") {
          console.log("Group expenses retrieved");
          console.log(response);
          setGroupExpenses(response.data.groupExpenses);
        } else {
          alert("");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <button onClick={handleTotalClick}>My Expenses</button>
      <button onClick={handleGroupClick}>Group Expenses</button>
      {showTotal && <p>Total Expenses: {showTotal}</p>}
      {showGroup && <GroupExpenses groupExpenses={groupExpenses} />}
    </div>
  );
}

export default Expenses;
