document.getElementById('itemForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const designer = document.getElementById('designer').value;
    const price = document.getElementById('price').value;

    fetch('/items', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, designer, price })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to add item');
            }
            return response.json();
        })
        .then(data => {
            console.log('Item added:', data);
            document.getElementById('itemForm').reset();
            fetchItems();
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

function fetchItems() {
    fetch('/items')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch items');
            }
            return response.json();
        })
        .then(data => {
            console.log('Items:', data);
            displayItems(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function displayItems(items) {
    const itemList = document.getElementById('itemList');
    itemList.innerHTML = '';

    items.forEach(item => {
        const itemItem = document.createElement('div');
        itemItem.innerHTML = <strong>Name:</strong> ${item.name}, <strong>Designer:</strong> ${item.designer}, <strong>Price:</strong> ${item.price};
        itemList.appendChild(itemItem);
    });
}


window.addEventListener('load', fetchItems);


fetch('/api/items')
    .then(response => response.json())
    .then(data => {
        console.log('Отримано дані з бекенду:', data);

    })
    .catch(error => {
        console.error('Помилка отримання даних з бекенду:', error);
    });


const newItems = {
    name: 'Новий товар',
    designer: 'Новий дизайнер',
    price: 'Нова ціна',
};

fetch('/api/items', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(newItems),
})
    .then(response => response.json())
    .then(data => {
        console.log('Товар доданий:', data);

    })
    .catch(error => {
        console.error('Помилка додавання товару:', error);
    });