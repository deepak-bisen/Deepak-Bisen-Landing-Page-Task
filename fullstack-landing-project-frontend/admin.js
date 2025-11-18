/**
 * Admin Panel JavaScript
 * * Handles tab switching, CRUD operations for Projects and Clients,
 * and the AI Chatbot interface.
 */

document.addEventListener('DOMContentLoaded', () => {

    // --- Tab Switching Logic ---
    const tabs = document.querySelectorAll('.tab-button');
    const panels = document.querySelectorAll('.tab-panel');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            // Deactivate all tabs
            tabs.forEach(t => {
                t.classList.remove('border-blue-500', 'text-blue-600');
                t.classList.add('border-transparent', 'text-gray-500', 'hover:text-gray-700', 'hover:border-gray-300');
                t.setAttribute('aria-current', 'false');
            });

            // Deactivate all panels
            panels.forEach(p => {
                p.classList.add('hidden');
            });

            // Activate the clicked tab
            tab.classList.add('border-blue-500', 'text-blue-600');
            tab.classList.remove('border-transparent', 'text-gray-500', 'hover:text-gray-700', 'hover:border-gray-300');
            tab.setAttribute('aria-current', 'page');

            // Activate the corresponding panel
            const targetPanelId = tab.id.replace('tab-', 'panel-');
            const targetPanel = document.getElementById(targetPanelId);
            if (targetPanel) {
                targetPanel.classList.remove('hidden');
            }
        });
    });

    // --- Project Management ---
    const projectForm = document.getElementById('project-form');
    const projectList = document.getElementById('project-list');
    const projectsLoading = document.getElementById('projects-loading');
    const formTitle = document.getElementById('form-title');
    const cancelEditBtn = document.getElementById('cancel-edit-btn');
    const projectIdField = document.getElementById('projectId');

    // API URL for projects
    const PROJECTS_API_URL = '/api/projects';

    /**
     * Renders the list of projects.
     * @param {Array} projects - An array of project objects.
     */
    const renderProjects = (projects) => {
        if (projects.length === 0) {
            projectList.innerHTML = '<p class="text-gray-500">No projects found. Add one above!</p>';
            return;
        }
        
        projectList.innerHTML = projects.map(project => `
            <div class="flex items-center justify-between p-4 border rounded-md shadow-sm" data-id="${project.id}">
                <div>
                    <h4 class="text-lg font-semibold">${escapeHTML(project.name)}</h4>
                    <p class="text-sm text-gray-600">${escapeHTML(project.category)}</p>
                    <p class="text-sm text-gray-500">${escapeHTML(project.description)}</p>
                </div>
                <div class="flex-shrink-0 flex space-x-2">
                    <button class="edit-project-btn bg-yellow-500 text-white px-3 py-1 rounded-md text-sm font-medium hover:bg-yellow-600">Edit</button>
                    <button class="delete-project-btn bg-red-600 text-white px-3 py-1 rounded-md text-sm font-medium hover:bg-red-700">Delete</button>
                </div>
            </div>
        `).join('');
    };

    /**
     * Fetches all projects from the API and renders them.
     */
    const loadProjects = async () => {
        try {
            const response = await fetch(PROJECTS_API_URL);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const projects = await response.json();
            renderProjects(projects);
            projectsLoading.classList.add('hidden');
        } catch (error) {
            console.error('Error fetching projects:', error);
            projectList.innerHTML = '<p class="text-red-500">Error loading projects. Please try again.</p>';
            projectsLoading.classList.add('hidden');
        }
    };

    /**
     * Resets the project form to its default state.
     */
    const resetProjectForm = () => {
        projectForm.reset();
        projectIdField.value = '';
        formTitle.textContent = 'Add New Project';
        cancelEditBtn.classList.add('hidden');
    };

    // Handle Project Form Submission (Add/Update)
    projectForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const formData = new FormData(projectForm);
        const project = Object.fromEntries(formData.entries());
        const projectId = project.id;
        
        const method = projectId ? 'PUT' : 'POST';
        const url = projectId ? `${PROJECTS_API_URL}/${projectId}` : PROJECTS_API_URL;

        try {
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(project),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
            }

            resetProjectForm();
            loadProjects(); // Reload the list
        } catch (error) {
            console.error('Error saving project:', error);
            alert(`Error saving project: ${error.message}`);
        }
    });

    // Handle Project List Clicks (Edit/Delete)
    projectList.addEventListener('click', async (e) => {
        const target = e.target;
        const projectItem = target.closest('[data-id]');
        if (!projectItem) return;
        
        const id = projectItem.dataset.id;

        // Handle Delete
        if (target.classList.contains('delete-project-btn')) {
            if (!confirm('Are you sure you want to delete this project?')) {
                return;
            }
            try {
                const response = await fetch(`${PROJECTS_API_URL}/${id}`, {
                    method: 'DELETE',
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                loadProjects(); // Reload the list
            } catch (error) {
                console.error('Error deleting project:', error);
                alert('Error deleting project. Please try again.');
            }
        }

        // Handle Edit
        if (target.classList.contains('edit-project-btn')) {
            try {
                const response = await fetch(`${PROJECTS_API_URL}/${id}`);
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const project = await response.json();
                
                // Populate the form
                projectIdField.value = project.id;
                document.getElementById('projectName').value = project.name;
                document.getElementById('imageUrl').value = project.imageUrl;
                document.getElementById('description').value = project.description;
                document.getElementById('category').value = project.category;
                
                formTitle.textContent = 'Edit Project';
                cancelEditBtn.classList.remove('hidden');
                projectForm.scrollIntoView({ behavior: 'smooth' });
            } catch (error) {
                console.error('Error fetching project for edit:', error);
                alert('Error fetching project details. Please try again.');
            }
        }
    });

    // Handle Cancel Edit
    cancelEditBtn.addEventListener('click', resetProjectForm);


    // --- Client Management ---
    const clientForm = document.getElementById('client-form');
    const clientList = document.getElementById('client-list');
    const clientsLoading = document.getElementById('clients-loading');
    const clientFormTitle = document.getElementById('client-form-title');
    const clientCancelEditBtn = document.getElementById('client-cancel-edit-btn');
    const clientIdField = document.getElementById('clientId');

    // API URL for clients
    const CLIENTS_API_URL = '/api/clients';

    /**
     * Renders the list of clients.
     * @param {Array} clients - An array of client objects.
     */
    const renderClients = (clients) => {
        if (clients.length === 0) {
            clientList.innerHTML = '<p class="text-gray-500">No clients found. Add one above!</p>';
            return;
        }

        clientList.innerHTML = clients.map(client => `
            <div class="flex items-center justify-between p-4 border rounded-md shadow-sm" data-id="${client.id}">
                <div class="flex items-center space-x-4">
                    <img src="${escapeHTML(client.avatarUrl)}" alt="${escapeHTML(client.name)}" class="w-16 h-16 rounded-full object-cover" onerror="this.src='https://placehold.co/100x100';">
                    <div>
                        <h4 class="text-lg font-semibold">${escapeHTML(client.name)}</h4>
                        <p class="text-sm text-gray-600">${escapeHTML(client.company)}</p>
                        <p class="text-sm text-gray-500 italic">"${escapeHTML(client.testimonial)}"</p>
                    </div>
                </div>
                <div class="flex-shrink-0 flex space-x-2">
                    <button class="edit-client-btn bg-yellow-500 text-white px-3 py-1 rounded-md text-sm font-medium hover:bg-yellow-600">Edit</button>
                    <button class="delete-client-btn bg-red-600 text-white px-3 py-1 rounded-md text-sm font-medium hover:bg-red-700">Delete</button>
                </div>
            </div>
        `).join('');
    };

    /**
     * Fetches all clients from the API and renders them.
     */
    const loadClients = async () => {
        try {
            const response = await fetch(CLIENTS_API_URL);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const clients = await response.json();
            renderClients(clients);
            clientsLoading.classList.add('hidden');
        } catch (error) {
            console.error('Error fetching clients:', error);
            clientList.innerHTML = '<p class="text-red-500">Error loading clients. Please try again.</p>';
            clientsLoading.classList.add('hidden');
        }
    };

    /**
     * Resets the client form to its default state.
     */
    const resetClientForm = () => {
        clientForm.reset();
        clientIdField.value = '';
        clientFormTitle.textContent = 'Add New Client';
        clientCancelEditBtn.classList.add('hidden');
    };

    // Handle Client Form Submission (Add/Update)
    clientForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const formData = new FormData(clientForm);
        const client = Object.fromEntries(formData.entries());
        const clientId = client.id;

        const method = clientId ? 'PUT' : 'POST';
        const url = clientId ? `${CLIENTS_API_URL}/${clientId}` : CLIENTS_API_URL;

        try {
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(client),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
            }

            resetClientForm();
            loadClients(); // Reload the list
        } catch (error) {
            console.error('Error saving client:', error);
            alert(`Error saving client: ${error.message}`);
        }
    });

    // Handle Client List Clicks (Edit/Delete)
    clientList.addEventListener('click', async (e) => {
        const target = e.target;
        const clientItem = target.closest('[data-id]');
        if (!clientItem) return;
        
        const id = clientItem.dataset.id;

        // Handle Delete
        if (target.classList.contains('delete-client-btn')) {
            if (!confirm('Are you sure you want to delete this client?')) {
                return;
            }
            try {
                const response = await fetch(`${CLIENTS_API_URL}/${id}`, {
                    method: 'DELETE',
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                loadClients(); // Reload the list
            } catch (error) {
                console.error('Error deleting client:', error);
                alert('Error deleting client. Please try again.');
            }
        }

        // Handle Edit
        if (target.classList.contains('edit-client-btn')) {
            try {
                const response = await fetch(`${CLIENTS_API_URL}/${id}`);
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const client = await response.json();
                
                // Populate the form
                clientIdField.value = client.id;
                document.getElementById('clientName').value = client.name;
                document.getElementById('avatarUrl').value = client.avatarUrl;
                document.getElementById('testimonial').value = client.testimonial;
                document.getElementById('company').value = client.company;
                
                clientFormTitle.textContent = 'Edit Client';
                clientCancelEditBtn.classList.remove('hidden');
                clientForm.scrollIntoView({ behavior: 'smooth' });
            } catch (error) {
                console.error('Error fetching client for edit:', error);
                alert('Error fetching client details. Please try again.');
            }
        }
    });

    // --- Initial Data Load ---
    loadProjects();
    loadClients();
});