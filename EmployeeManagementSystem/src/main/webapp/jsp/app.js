let employees = [
  {name:"Amit", dept:"IT", salary:50000},
  {name:"Ravi", dept:"HR", salary:40000},
  {name:"Neha", dept:"Finance", salary:60000},
  {name:"Sima", dept:"IT", salary:55000},
  {name:"Rahul", dept:"Admin", salary:30000},
  {name:"Karan", dept:"IT", salary:70000},
  {name:"Pooja", dept:"HR", salary:45000}
];

let currentPage = 1;
let rowsPerPage = 3;

function displayTable() {
  let table = document.getElementById("tableBody");
  table.innerHTML = "";

  let start = (currentPage - 1) * rowsPerPage;
  let end = start + rowsPerPage;

  let pageData = employees.slice(start, end);

  pageData.forEach((emp, index) => {
    table.innerHTML += `
      <tr>
        <td>${emp.name}</td>
        <td>${emp.dept}</td>
        <td>${emp.salary}</td>
        <td><button class="action-btn" onclick="deleteRow(${start + index})">Delete</button></td>
      </tr>
    `;
  });

  document.getElementById("pageNum").innerText = currentPage;
}

function nextPage() {
  if(currentPage * rowsPerPage < employees.length) {
    currentPage++;
    displayTable();
  }
}

function prevPage() {
  if(currentPage > 1) {
    currentPage--;
    displayTable();
  }
}

function sortTable(col) {
  if(col === 0) {
    employees.sort((a,b)=>a.name.localeCompare(b.name));
  } 
  else if(col === 1) {
    employees.sort((a,b)=>a.dept.localeCompare(b.dept));
  } 
  else if(col === 2) {
    employees.sort((a,b)=>a.salary - b.salary);
  }

  displayTable();
}

function deleteRow(index) {
  employees.splice(index,1);
  displayTable();
}

function addEmployee() {
  let name = prompt("Enter Name");
  let dept = prompt("Enter Department");
  let salary = prompt("Enter Salary");

  employees.push({name, dept, salary});
  displayTable();
}

displayTable();