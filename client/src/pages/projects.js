import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getProjects, createProject, getQualifications } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle, createProjectButtonStyle, formStyle, labelStyle, inputStyle, qualListStyle, qualItemStyle, buttonRowStyle, buttonStyle } from '../utils/styles'

const Project = (project, active) => {
   return (
       <div>
           <div>{project.name}</div>
           {active === true ? ProjectBody(project) : null}
       </div>
   )
}

const ProjectBody = (project) => {
    let styles = project.qualifications.map((qualification) => project.missingQualifications.includes(qualification) ? { backgroundColor: 'Tomato'}: { backgroundColor: 'LightGreen'})
    return (
        <div style={grayContainerStyle}>
            <div>
                Name: {project.name}
            </div>

            <div>
                Size: {project.size}
            </div>

            <div>
                Status: {project.status}
            </div>

            <div>
                Workers: <ClickList list={project.workers} styles={darkGrayContainerStyle} path="/workers" />
            </div>

            <div>
                Qualifications: <ClickList list={project.qualifications} styles={styles} path="/qualifications" />
            </div>

            
            
        </div>
    )
}

const PROJECT_SIZES = ['SMALL', 'MEDIUM', 'BIG']

const CreateProjectForm = ({ onCancel, onCreated }) => {
    const [name, setName] = useState('')
    const [size, setSize] = useState('SMALL')
    const [allQualifications, setAllQualifications] = useState([])
    const [selectedQualifications, setSelectedQualifications] = useState([])
    const [error, setError] = useState('')
    const [submitting, setSubmitting] = useState(false)
    useEffect(() => {getQualifications().then(setAllQualifications)}, [])
    const handleQualificationToggle = (description) => {setSelectedQualifications(prev =>prev.includes(description) ? prev.filter(q => q !== description) : [...prev, description])}

    const handleSubmit = async () => {
        if (!name.trim()) { setError('Project needs a name'); return }
        if (selectedQualifications.length === 0) { setError('Every project must have at least one qualification'); return }

        setSubmitting(true)
        setError('')
        try {
            await createProject(name.trim(), selectedQualifications, size)
            onCreated()
        } catch (e) {
            setError(e?.response?.data || 'Failed to create project.')
            setSubmitting(false)
        }
    }

    return (
        <div style={formStyle}>
            <div>
                <label style={labelStyle}>Project Name</label>
                <input
                    style={inputStyle}
                    type="text"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </div>

            <div>
                <label style={labelStyle}>Project Size</label>
                <select style={inputStyle} value={size} onChange={e => setSize(e.target.value)}>
                    {PROJECT_SIZES.map(s => (
                        <option key={s} value={s}>{s.charAt(0) + s.slice(1).toLowerCase()}</option>
                    ))}
                </select>
            </div>

            <div>
                <label style={labelStyle}>
                    Qualifications ({selectedQualifications.length} selected)
                </label>
                <div style={qualListStyle}>
                    {allQualifications.length === 0 ? <div>No Qualifications to Choose From</div>
                    : allQualifications.map(q => (
                            <div
                                key={q.description}
                                style={qualItemStyle(selectedQualifications.includes(q.description))}
                                onClick={() => handleQualificationToggle(q.description)}
                            >
                                {q.description}
                            </div>
                        ))
                    }
                </div>
            </div>

            {error && <div style={{ backgroundColor: 'Tomato'}}>{error}</div>}

            <div style={buttonRowStyle}>
                <button style={buttonStyle} onClick={handleSubmit} disabled={submitting}>
                    {submitting ? 'Creating...' : 'Create Project'}
                </button>
                <button style={buttonStyle} onClick={onCancel}>Cancel</button>
            </div>
        </div>
    )
}

const Projects = () => {
   const [projects, setProjects] = useState([])
   const [showForm, setShowForm] = useState(false)
    useEffect(() => { getProjects().then(setProjects) }, [])
    const loadProjects = () => getProjects().then(setProjects)
    useEffect(() => { loadProjects() }, [])

    const active = LocationID('projects', projects, 'name')
    const handleCreated = () => {
        setShowForm(false)
        loadProjects()
    }

    return (
        <div style={pageStyle}>
            <h1>Company Projects</h1>
            <ClickList active={active} list={projects} item={Project} path='/projects' id='name' />

            {!showForm && (
                <button  style={createProjectButtonStyle}onClick={() => setShowForm(true)}>
                    + Create Project
                </button>
            )}
            {showForm && (
                <CreateProjectForm
                    onCancel={() => setShowForm(false)}
                    onCreated={handleCreated}
                />
            )}
        </div>
    )
}

export default Projects