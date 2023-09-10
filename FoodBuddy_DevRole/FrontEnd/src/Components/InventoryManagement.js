import React, { useEffect, useState } from "react";
import "react-datepicker/dist/react-datepicker.css";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";

export const InventoryManagement = () => {

   const [data, setData] = useState([]);
   const [editIndex, setEditIndex] = useState(-1);
   const [newName, setNewName] = useState("");
   const [newQuantity, setQuantity] = useState(0);
   const [newExpiry, setExpiry] = useState("");
   const [newAmount, setAmount] = useState(0);
   const [currentId, setCurrentId] = useState(1);

  const [viewData, setviewData] = useState([]);
  const userGroupNumber = useSelector(
    (state) => state.groupManagementReducer.groupCode
  );
  const emailId = useSelector((state) => state.loginLogoutReducer.emailId);

  useEffect(() => {
    axios
      .get(`http://172.17.0.203:8080/api/v1/inventory/view?groupCode=${userGroupNumber}`)
      .then((response) => {
        console.log(response.data.itemList);
        setData( response.data.itemList);
      });
  }, []);

  const handleEdit = (index) => {
    setEditIndex(index);
    setNewName(data[index].itemName);
    setQuantity(data[index].quantity);
    setExpiry(data[index].expiry);
    setAmount(data[index].amount);
  };

  const handleSave = (index) => {
    const updatedData = [...data];
    updatedData[index] = {
      id: data[index].id,
      itemName: newName,
      quantity: newQuantity,
      expiry: newExpiry,
      amount: newAmount,
      emailId:emailId
    };
    setData(updatedData);
    setEditIndex(-1);
    setNewName("");
    setQuantity("");
    setExpiry("");
    setAmount("");

    const formData = {
      itemName: newName,
      quantity: newQuantity,
      expDate: newExpiry,
      groupCode: userGroupNumber,
      amount: newAmount,
      emailId:emailId

    };
    console.log(formData);
    saveItem(formData);
  };

  const saveItem = (formData) => {
    axios
      .post("http://172.17.0.203:8080/api/v1/inventory/add", formData)
      .then((response) => {
        if (response.data.status === "success") {
          console.log("Item added");
        } else {
          alert("");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleCancel = () => {
    setEditIndex(-1);
    setNewName("");
    setQuantity("");
    setExpiry("");
    setAmount("");
  };

  const handleNotification = (index) => {
      const updatedData = [...data];
      updatedData[index] = {
        emailId:emailId,

      };
      const formData = {
        groupCode: userGroupNumber,
        itemName: data[index].itemName,
      };
      console.log(formData);
      sendNotification(formData);
    };
   const sendNotification = (formData) => {
       axios
         .post("http://172.17.0.203:8080/api/v1/notification/notify", formData)
         .then((response) => {
          console.log(response.data);
          console.log(response);
           if (response.data === "success") {
             console.log("Email sent to group members");
             alert("Email sent to group members");
           } else {
             alert("Could Not send email. Please try again later");
           }
         })
         .catch((error) => {
           console.log(error);
         });
     };
  const handleDelete = (index) => {
    setEditIndex(-1);
    const updatedData = data.filter((item) => item.id !== data[index].id);
    setData(updatedData);
    const formData = {
      itemName: data[index].itemName,
      quantity: newQuantity,
      expDate: newExpiry,
      groupCode: userGroupNumber,
      amount: data[index].amount,
      emailId:emailId

    };
    console.log(formData);
    deleteItem(formData);

  };
  const deleteItem = (formData) => {
      axios
        .post("http://172.17.0.203:8080/api/v1/inventory/delete", formData)
        .then((response) => {
          if (response.data.status === "success") {
            console.log("Item added");
            //
          } else {
            alert("");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };


  const handleAdd = () => {
    const newId = data.length + 1;
    const newData = {
      id: currentId,
      name: "",
      quantity: "",
      expiry: "",
      amount: "",
    };
    setData([...data, newData]);
    setEditIndex(newId - 1);
    setCurrentId(currentId + 1);
  };

  const handleDateSelect = (event) => {
    setExpiry(event.target.value);
  };

  return (
    <div>
      <table>
              <button onClick={handleAdd}>Add Item</button>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Item Name</th>
                  <th>Quantity</th>
                  <th>Expiry Date</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody>
                {data.map((item, index) => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>
                      {editIndex === index ? (
                        <input
                          type="text"
                          value={newName}
                          onChange={(e) => setNewName(e.target.value)}
                        />
                      ) : (
                        item.itemName
                      )}
                    </td>
                    <td>
                      {editIndex === index ? (
                        <input
                          type="number"
                          value={newQuantity}
                          onChange={(e) => setQuantity(e.target.value)}
                        />
                      ) : (
                        item.quantity
                      )}
                    </td>
                    <td>
                      <input type="date" id="date" value={item.expDate} onChange={handleDateSelect} />
                    </td>
                    <td>
                      {editIndex === index ? (
                        <input
                          type="text"
                          value={newAmount}
                          onChange={(e) => setAmount(e.target.value)}
                        />
                      ) : (
                        item.amount
                      )}
                    </td>
                    <td>
                      {editIndex === index ? (
                        <>
                          <button onClick={() => handleSave(index)}>Save</button>
                          <button onClick={handleCancel}>Cancel</button>
                        </>
                      ) : (
                        <>
                          <button onClick={() => handleEdit(index)}>Edit</button>
                          <button onClick={() => handleDelete(index)}>Delete</button>
                          <button onClick={() => handleNotification(index)}>Notify</button>

                        </>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
    </div>
  );
};

export default InventoryManagement;
