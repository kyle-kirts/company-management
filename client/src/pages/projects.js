import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { getProjects, createProject, getQualifications, getWorkers, start, finish, assignWorker, unassignWorker  } from '../services/dataService'
import LocationID from '../utils/location'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle, createProjectButtonStyle, formStyle, labelStyle, inputStyle, qualListStyle, qualItemStyle, buttonRowStyle, buttonStyle } from '../utils/styles'

const Project = (project, active, loadProjects) => {
   return (
       <div>
           <div>{project.name}</div>
           {active === true ? <ProjectBody {...project} loadProjects={loadProjects}/> : null}
       </div>
   )
}
const CreateAssignForm = ({ project, onCancel, onCreated }) => {
    const [helpfulWorkers, setHelpfulWorkers] = useState([])
    const [selectedWorker, setSelectedWorker] = useState('')
    const [error, setError] = useState('')
    const [submitting, setSubmitting] = useState(false)

    useEffect(() => {
    getWorkers().then(workers => {
        const helpful = workers.filter(w =>
            w.qualifications.some(q => project.missingQualifications.includes(q)) &&
            !project.workers.includes(w.name)
        )
        setHelpfulWorkers(helpful)
    })
}, [project.missingQualifications, project.workers])

    const handleSubmit = async () => {
        if (!selectedWorker) { setError('Please select a worker to assign.'); return }
        setSubmitting(true)
        setError('')
        try {
            await assignWorker(project.name, selectedWorker)
            onCreated()
        } catch (e) {
            setError(e?.response?.data || 'Failed to assign worker.')
            setSubmitting(false)
        }
    }

    return (
        <div style={formStyle}>
            <div>
                <label style={labelStyle}>Assign Worker</label>
                <div style={qualListStyle}>
                    {helpfulWorkers.length === 0
                        ? <div>No helpful workers available</div>
                        : helpfulWorkers.map(w => (
                            <div
                                key={w.name}
                                style={qualItemStyle(selectedWorker === w.name)}
                                onClick={(e) => { e.stopPropagation(); setSelectedWorker(w.name) }}
                            >
                                {w.name}
                            </div>
                        ))
                    }
                </div>
            </div>

            {error && <div style={{ backgroundColor: 'Tomato' }}>{error}</div>}

            <div style={buttonRowStyle}>
                <button style={buttonStyle} onClick={(e) => { e.stopPropagation(); handleSubmit() }} disabled={submitting}>
                    {submitting ? 'Assigning...' : 'Assign'}
                </button>
                <button style={buttonStyle} onClick={(e) => { e.stopPropagation(); onCancel() }}>Cancel</button>
            </div>
        </div>
    )
}

const CreateUnassignForm = ({ project, onCancel, onCreated }) => {
    const [selectedWorker, setSelectedWorker] = useState('')
    const [error, setError] = useState('')
    const [submitting, setSubmitting] = useState(false)

    const handleSubmit = async () => {
        if (!selectedWorker) { setError('Please select a worker to unassign.'); return }
        setSubmitting(true)
        setError('')
        try {
            await unassignWorker(project.name, selectedWorker)
            onCreated()
        } catch (e) {
            setError(e?.response?.data || 'Failed to unassign worker.')
            setSubmitting(false)
        }
    }

    return (
        <div style={formStyle}>
            <div>
                <label style={labelStyle}>Unassign Worker</label>
                <div style={qualListStyle}>
                    {project.workers.length === 0
                        ? <div>No workers currently assigned</div>
                        : project.workers.map(w => (
                            <div
                                key={w}
                                style={qualItemStyle(selectedWorker === w)}
                                onClick={(e) => { e.stopPropagation(); setSelectedWorker(w) }}
                            >
                                {w}
                            </div>
                        ))
                    }
                </div>
            </div>

            {error && <div style={{ backgroundColor: 'Tomato' }}>{error}</div>}

            <div style={buttonRowStyle}>
                <button style={buttonStyle} onClick={(e) => { e.stopPropagation(); handleSubmit() }} disabled={submitting}>
                    {submitting ? 'Unassigning...' : 'Unassign'}
                </button>
                <button style={buttonStyle} onClick={(e) => { e.stopPropagation(); onCancel() }}>Cancel</button>
            </div>
        </div>
    )
}

const ProjectBody = (project) => {
    let styles = project.qualifications.map((qualification) => project.missingQualifications.includes(qualification) ? { backgroundColor: 'Tomato'}: { backgroundColor: 'LightGreen'})
    const { loadProjects } = project
    const [status, setStatus] = useState(project.status)
    const [actionError, setActionError] = useState('')
     const [showAssign, setShowAssign] = useState(false)
    const [showUnassign, setShowUnassign] = useState(false)
    const canStart = (status === 'PLANNED' || status === 'SUSPENDED') && project.missingQualifications.length === 0
    const canFinish = status === 'ACTIVE'
    const canAssign = status !== 'ACTIVE' && status !== 'FINISHED'
    const canUnassign = project.workers.length > 0


    const handleStart = async (e) => {
        e.stopPropagation()
        setActionError('')
        try {
            await start(project.name)
            setStatus('ACTIVE')
        } catch (error) {
            setActionError(error?.response?.data || 'Failed to start project.')
        }
    }

    const handleFinish = async (e) => {
        e.stopPropagation()
        setActionError('')
        try {
            await finish(project.name)
            setStatus('FINISHED')
            loadProjects()
        } catch (error) {
            setActionError(error?.response?.data || 'Failed to finish project.')
        }
    }

    return (
        <div style={grayContainerStyle}>
            <div>
                Name: {project.name}
            </div>

            <div>
                Size: {project.size}
            </div>

            <div>
                Status: {status}
            </div>

            <div>
                Workers: <ClickList list={project.workers} styles={darkGrayContainerStyle} path="/workers" />
            </div>

            <div>
                Qualifications: <ClickList list={project.qualifications} styles={styles} path="/qualifications" />
            </div>

            {actionError && <div style={{ backgroundColor: 'Tomato' }}>{actionError}</div>}
            <div style={buttonRowStyle}>
                <button style={buttonRowStyle} onClick={(e) => handleStart(e)} disabled={!canStart}>Start</button>
                <button style={buttonRowStyle} onClick={(e) => handleFinish(e)} disabled={!canFinish}>Finish</button>
                <button onClick={(e) => { e.stopPropagation(); setShowAssign(true); setShowUnassign(false) }} disabled = {!canAssign}>Assign</button>
                <button onClick={(e) => { e.stopPropagation(); setShowUnassign(true); setShowAssign(false) }} disables = {!canUnassign}>Unassign</button>
            </div>
            {showAssign && (
                <CreateAssignForm
                    project={project}
                    onCancel={() => setShowAssign(false)}
                    onCreated={() => { setShowAssign(false); loadProjects() }}
                />
            )}

            {showUnassign && (
                <CreateUnassignForm
                    project={project}
                    onCancel={() => setShowUnassign(false)}
                    onCreated={() => { setShowUnassign(false); loadProjects() }}
                />
            )}
            
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
    const ProjectItem = (project, active) => Project(project, active, loadProjects)
    return (
        <div style={pageStyle}>
            <h1>Company Projects</h1>
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
            <ClickList active={active} list={projects} item={ProjectItem} path='/projects' id='name' />
        </div>
    )
}

export default Projects